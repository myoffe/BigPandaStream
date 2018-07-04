## Running it
1. Open in IntelliJ
2. Find the `Main` class in the project view under `src/main/java`
3. Right click -> `Run Main.main()`

## API
```
http://localhost:4567/words
http://localhost:4567/events
```

## Things I would improve
1. To make `Consumer` unit-testable, I'd extract use of the generator process into another class, so we can mock it.
2. I'd use Lombok, or just Scala (or Kotlin...), to make `Event` a simple data class (i.e. auto generate fields, setters, etc.)
3. I'd find or implement a `defaultdict`-style (from Python) type structure to use instead of the `Map<String, Integer>`s in `Stats`,
so we won't need the `if (!map.contains(..)) map.put(thing, 0)`
4. Make `stats` not static in `Main`, to make it more testable.
5. This is a bit out of scope, but holding the stats in memory won't scale for large distributions of event types and words,
so we'd need some persistance there.
6. Output responses in JSON, instead of strings, so it will be consumable by other services.

Honestly though, I would probably not do any of these things, since it's so little amount of code, and it fulfills the requirements and has good enough performance.
In short: YAGNI :)
