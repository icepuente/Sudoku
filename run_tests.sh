#!/bin/bash

# Sudoku Project Test Runner
# Uses local JUnit 4 JAR (no network required)

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Sudoku Project Test Runner ===${NC}"
echo ""

# Project directories
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
BUILD_DIR="${PROJECT_DIR}/build/classes"
TEST_BUILD_DIR="${PROJECT_DIR}/build/test-classes"

# JUnit JAR locations (from Gradle installation)
JUNIT_JAR="/opt/gradle-8.14.3/lib/junit-4.13.2.jar"
HAMCREST_JAR="/opt/gradle-8.14.3/lib/hamcrest-core-1.3.jar"

# Verify JUnit JARs exist
if [ ! -f "$JUNIT_JAR" ]; then
    echo -e "${RED}Error: JUnit JAR not found at $JUNIT_JAR${NC}"
    exit 1
fi

# Create build directories
mkdir -p "${BUILD_DIR}"
mkdir -p "${TEST_BUILD_DIR}"

# Build classpath
CLASSPATH="${JUNIT_JAR}:${HAMCREST_JAR}:${BUILD_DIR}:${TEST_BUILD_DIR}"

# Compile source files
# Note: Source files have 'package sudoku;' but are in root directory
echo -e "${YELLOW}Compiling source files...${NC}"
cd "${PROJECT_DIR}"

# Compile all Java files from root directory
javac -d "${BUILD_DIR}" \
    Difficulty.java \
    MainBoard.java \
    Tools.java \
    Save.java \
    GameView.java \
    Login.java \
    MainMenu.java \
    LeaderBoard.java \
    NewLogin.java \
    Sudoku.java \
    2>&1 | grep -v "^Note:" || true
echo -e "${GREEN}Source compilation complete.${NC}"

# Compile test files
echo -e "${YELLOW}Compiling test files...${NC}"
javac -cp "${CLASSPATH}" -d "${TEST_BUILD_DIR}" \
    test/sudoku/DifficultyTest.java \
    test/sudoku/MainBoardTest.java \
    test/sudoku/ToolsTest.java \
    test/sudoku/SaveTest.java
echo -e "${GREEN}Test compilation complete.${NC}"
echo ""

# Run tests
echo -e "${YELLOW}Running tests...${NC}"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo ""

# Run JUnit text runner for each test class
TEST_CLASSES="sudoku.DifficultyTest sudoku.MainBoardTest sudoku.ToolsTest sudoku.SaveTest"

TOTAL_TESTS=0
PASSED_TESTS=0
FAILED_TESTS=0

for TEST_CLASS in $TEST_CLASSES; do
    echo -e "${BLUE}Running: ${TEST_CLASS}${NC}"

    # Run the test and capture output
    set +e
    java -cp "${CLASSPATH}" org.junit.runner.JUnitCore "${TEST_CLASS}" 2>&1
    RESULT=$?
    set -e

    if [ $RESULT -eq 0 ]; then
        ((PASSED_TESTS++)) || true
    else
        ((FAILED_TESTS++)) || true
    fi
    ((TOTAL_TESTS++)) || true
    echo ""
done

echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo ""

if [ $FAILED_TESTS -eq 0 ]; then
    echo -e "${GREEN}=== All ${TOTAL_TESTS} test classes passed! ===${NC}"
else
    echo -e "${RED}=== ${FAILED_TESTS} of ${TOTAL_TESTS} test classes failed ===${NC}"
    exit 1
fi
