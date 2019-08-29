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
```

### Development
#### Running locally
* clone this repo
* `./gradlew bootRun`