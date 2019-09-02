### GraphQL API

#### Query
* getting user wall
```gql
query test($input: UserWallInput) {
  userWall(input: $input) {
    message
  }
}
```
* payload
```json
{
  "input": {
  "authorId": "b8389d9f-d758-4831-a69b-199f389e7d6b",
    "count": 100,
    "offset": 0,
    "withFollowersTweets": true
  }
}
```

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
    followers {
      name
      id
    }
    tweets {
      message
      id
    }
  }

  tweetsByAuthor(authorId: "4c309ddc-9cbc-4154-a698-880498c1e717", count: 10, offset: 0) {
    message
  }
  tweets(count: 10, offset: 0) {
    id
    message
    authorId
  }
}
```
#### Mutation
```gql

mutation addAuthor {
  addAuthor(name: "test") {
    id
    name
  }
}

mutation addTweet {
  addTweet(input: "<message>", author: "<author_id>") {
    authorId
    id
    message
  }
}
```
### Development
#### Running locally
* clone this repo
* `./gradlew bootRun`
* playground endpoint `http://localhost:9000/graphiql`

### Maybe maybe
* better semantics https://github.com/palatable/lambda