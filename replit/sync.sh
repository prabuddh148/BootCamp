#!/usr/bin/env bash
# Keeps this Repl and GitHub (master) in sync.
#
# The Run button starts it for you. By hand:
#   start:  bash replit/sync.sh --bg
#   log:    tail -f /tmp/replit-sync.log
#   stop:   kill $(cat /tmp/replit-sync.pid)
#
# Pushing needs a GitHub token in Replit Secrets named GITHUB_TOKEN.

INTERVAL=5        # seconds between checks
QUIET_CHECKS=2    # commit only after files stop changing for this many checks (~10s)
BRANCH=master
PIDFILE=/tmp/replit-sync.pid
LOG=/tmp/replit-sync.log

SCRIPT="$(cd "$(dirname "$0")" && pwd)/$(basename "$0")"
cd "$(git -C "$(dirname "$SCRIPT")" rev-parse --show-toplevel)" || exit 1

if [ "$1" = "--bg" ]; then
    if [ -f "$PIDFILE" ] && kill -0 "$(cat "$PIDFILE")" 2>/dev/null; then
        exit 0
    fi
    nohup bash "$SCRIPT" >>"$LOG" 2>&1 &
    echo $! >"$PIDFILE"
    echo "[sync] GitHub sync chalu (log: $LOG)"
    exit 0
fi

log() { echo "$(date '+%H:%M:%S') $*"; }

git config user.name >/dev/null || git config user.name "prabuddh148"
git config user.email >/dev/null || git config user.email "prabuddh148@users.noreply.github.com"

# Send the token as a header so it never ends up in a URL or the log
AUTH=()
if [ -n "$GITHUB_TOKEN" ]; then
    BASIC="$(printf 'x-access-token:%s' "$GITHUB_TOKEN" | base64 | tr -d '\n')"
    AUTH=(-c "http.https://github.com/.extraheader=Authorization: Basic $BASIC")
else
    log "GITHUB_TOKEN secret nahi mila: GitHub se pull hoga, push nahi."
fi

# Fingerprint of everything uncommitted, including new files' contents
snapshot() {
    git status --porcelain
    git diff
    git ls-files -o --exclude-standard -z | xargs -0 cat 2>/dev/null | md5sum
}

last=""
stable=0
log "sync shuru ($BRANCH, har ${INTERVAL}s)"

while true; do
    if [ -n "$(git status --porcelain)" ]; then
        now="$(snapshot)"
        if [ "$now" = "$last" ]; then stable=$((stable + 1)); else stable=0; fi
        last="$now"
        if [ "$stable" -ge "$QUIET_CHECKS" ]; then
            git add -A
            git commit -q -m "Replit autosave $(date -u '+%Y-%m-%d %H:%M:%S UTC')" && log "commit kiya"
            stable=0
            last=""
        fi
    fi

    if git "${AUTH[@]}" fetch -q origin "$BRANCH"; then
        if [ "$(git rev-list --count "HEAD..origin/$BRANCH")" -gt 0 ]; then
            if git rebase -q --autostash "origin/$BRANCH"; then
                log "GitHub se naye changes liye"
            else
                git rebase --abort 2>/dev/null
                log "CONFLICT: GitHub aur Replit dono pe same jagah badlaav. Git tab se haath se theek karo."
            fi
        fi
        if [ -n "$GITHUB_TOKEN" ] && [ "$(git rev-list --count "origin/$BRANCH..HEAD")" -gt 0 ]; then
            git "${AUTH[@]}" push -q origin "HEAD:$BRANCH" && log "GitHub pe push kiya"
        fi
    else
        log "GitHub tak nahi pahunch paaye, agli baar try karenge"
    fi

    sleep "$INTERVAL"
done
