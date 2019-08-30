### GraphQL API

#### Query
* getting all tweets
```gql
{
  tweets(count: 10, offset: 0) {
    id
    message
  }
}
```

* getting all authors
```gql
{
  authors {
    id
    name
  }
}

* adding authors
```gql
mutation appendData {
  addAuthor(name: "author-name") {
    id
    name
  }
}
```

### Development
#### Running locally
* clone this repo
* `./gradlew bootRun`