# Basket Splitter

Basket Splitter is a Java library that splits items in a customer's basket into delivery groups. The goal is to minimize the number of required deliveries and to create the largest possible delivery groups. 
My solution provides greedy approach that doesn't guarantee the optimal solution, but it is fast and provides good results in most cases and works way faster
than the brute force or DP solution even for the large input such as $2^{100}$.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17 or 21
- Gradle

### Installing

1. Clone the repository
```bash
git clone https://github.com/Gawor270/BasketSplitter
```

2. Navigate into the cloned repository
```bash
cd BasketSplitter
```

3. Build the project
```bash
./gradlew build
```

## Usage

To use the BasketSplitter library, you need to create an instance of the `BasketSplitter` class and call the `split` method.

```java
BasketSplitter splitter = new BasketSplitter("path/to/config.json");
List<String> items = Arrays.asList("item1", "item2", "item3");
Map<String, List<String>> result = splitter.split(items);
```

## Running the tests

To run the tests, use the following command:

```bash
./gradlew test
```
