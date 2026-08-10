# BootCamp - Java Project

Standard Java project structure following IntelliJ IDEA conventions.

## Project Structure

```
BootCamp/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               └── bootcamp/
│   │                   └── HelloWorld.java
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── bootcamp/
├── out/                  (compiled bytecode)
└── README.md
```

## How to Compile and Run

**Compile all Java files:**
```bash
javac -d out src/main/java/com/example/bootcamp/*.java
```

**Run the program:**
```bash
java -cp out com.example.bootcamp.HelloWorld
```

## Package Naming Convention

Java uses reverse domain notation for packages:
- `com.example.bootcamp` → Folder structure: `com/example/bootcamp/`
- Each file starts with: `package com.example.bootcamp;`

## Quick Commands

- **Compile**: `javac -d out src/main/java/com/example/bootcamp/*.java`
- **Run**: `java -cp out com.example.bootcamp.HelloWorld`
- **Compile recursive**: `javac -d out -sourcepath src/main/java src/main/java/com/example/bootcamp/*.java`
