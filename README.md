# COMP16412 Practice Exams — Self-Timed Attempts

Java solutions written under self-imposed exam conditions, practising for the University of Manchester COMP16412 (Introduction to Programming II) practical exam. Each folder corresponds to one full past paper, attempted solo and timed to match the real exam (2 hours, 40 marks: 25 for Section 1, 15 for Section 2).

Both exams scored full marks on the Gradescope autograder after enough tries.

## Overview

| Exam | Title | Package |
|------|-------|---------|
| [1](./Mock%20exam) | Resit Exam (Practical) - 2024-25 | traitors |
| [2](./Sample%20exam) | Sample Exam | library |


Each folder contains the original exam paper (PDF) alongside the package of `.java`/`.class` files written in response.

## Sample exam — `library` package

Based on `Sample exam/COMP16412 Sample Exam.pdf`. Models a simple library system.

- **`Genre.java`** — enum of book genres (`CHILDRENS`, `CRIME`, `FANTASY`, `HISTORICAL_FICTION`, `NON_FICTION`, `SCIENCE_FICTION`).
- **`Book.java`** — attributes (title, author, publication year, genre), getters, `toString`, and overridden `equals`/`hashCode` based on title and author (case-sensitive, exact match).
- **`Library.java`** — holds a `List<Book>`; supports `addBook`, `removeBook` (throwing `IndexOutOfBoundsException` on a bad index), and `getUniqueBooks` (returns a `Set<Book>` relying on `Book`'s `equals`/`hashCode`). Also implements `findFictionBooks(Integer publishedInOrAfter, String searchTerm)` using the Stream API, filtering out `NON_FICTION` and applying the year/title filters only when the corresponding parameter is non-null.
- **`BookComparator.java`** — a `Comparator<Book>` implementing the required four-level sort: title (case-insensitive) → author reordered to "lastname, firstname" (case-insensitive) → publication year (descending, newer first) → genre name (alphabetic).
- **`getSortedBooks`** (in `Library.java`) — combines `getUniqueBooks` and `BookComparator` to return a duplicate-free, sorted `ArrayList<Book>` without mutating the library's underlying list.

### Self-marking notes
- All parts are present and complete — confirmed full marks (40/40) on the Gradescope autograder.

## Mock exam — `traitors` package

Based on `Mock exam/COMP16412 Resit Exam (Practical) - 2024-25.pdf`. Models the social deduction game *The Traitors*. All four classes specified across both sections were completed.

- **`Player.java`** — base class with `game` and `name` attributes (provided as starter code rather than written from scratch). `getVote(ArrayList<Player> players)` randomly selects another player to vote for, re-rolling (via `equals`, i.e. reference identity, since `Player` doesn't override it) if the index picked is itself.
- **`Faithful.java`** — trivial subclass of `Player`.
- **`Traitor.java`** — subclass of `Player` with a private constructor and a static `recruit(Faithful faithful)` factory method that converts a `Faithful` into a `Traitor`, swapping it into the same position in the `Game`'s `players` list.
- **`RoundTable.java`** — manages a single day's vote:
  - `collectVotes()` — calls `getVote` on every player, tallies results into a `HashMap<Player, ArrayList<Player>>`, and returns whether a single player has a strict plurality.
  - `resolveTie()` — re-votes only among the tied players (without recursion), updates `votes`, and returns whether the tie is now broken.
  - `getBanishedPlayer()` — returns the player with the most votes, or throws `IllegalStateException` if the result is ambiguous.
- **`Game.java`** — ties everything together:
  - `players`, `random`, and `banished` attributes, set up in the constructor (which takes the `Random` instance and initialises both lists empty).
  - `getRandom`/`getPlayers` getters, plus a `setPlayers` used by `Traitor.recruit` to swap a `Faithful` for a `Traitor` in place.
  - `initialisePlayers(String[] names)` — creates a `Faithful` for each name, then picks three distinct random indices (re-rolling on duplicates) and recruits those three as `Traitor`s.
  - `runRoundTable()` — creates a `RoundTable` from the current players, calls `collectVotes` once and then `resolveTie` as many times as needed to break all ties, removes the resulting `getBanishedPlayer()` from `players`, adds them to `banished`, and returns the total number of voting cycles.

### Self-marking notes
- All parts are present and complete — confirmed full marks (40/40) on the Gradescope autograder.
- `getVote`'s self-exclusion check relies on `Player` not overriding `equals` (so it falls back to reference identity) — this works correctly given how players are stored and compared throughout, but is worth being aware of if `equals` is ever added to `Player` later.

## Requirements

- **JDK 17+** (both solutions use generics, streams, and enums comfortably supported by any recent JDK).
- No external dependencies — everything compiles with the standard library alone.

## Compiling and running

Both exams expect code to live in a named package and be compiled/zipped from one level above that package folder (this matches the Gradescope upload format described in each exam paper).

### Sample exam (`library` package)

```bash
cd "Sample exam"
javac library/*.java
```

There's no `main` method provided in this package — it was written purely against the spec, so testing would mean adding a small driver class or JUnit tests calling `Library`/`Book`/`BookComparator` directly.

### Mock exam (`traitors` package)

```bash
cd "Mock exam"
javac traitors/*.java traitors/players/*.java
```

 To try it out, a small driver could be added.

## Notes

- Compiled `.class` files are committed alongside the source — safe to delete and regenerate with `javac`.
- These are exam *attempts*, not verified-correct solutions — the self-marking notes above flag the spots most worth re-checking against the official spec before treating them as a reference.
