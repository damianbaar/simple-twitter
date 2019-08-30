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
* getting stuff
```gql
{
  authors{
    id
    name
  }
  tweetsByAuthor(authorId: "<author_id>", count: 10, offset: 0) {
    message
  }
  tweets(count: 10, offset: 0) {
    id
    message
    authorId
  }
}
```

### Development
#### Running locally
* clone this repo
* `./gradlew bootRun`