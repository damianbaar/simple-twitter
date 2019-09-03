# Backend for Twitter like message app
Simple app to create `tweets-like` messages with simple `follow` user mechanism. \
Based upon `GraphQL` and `SpringBoot`.

## Working scenarios
### Posting
* A user should be able to post a 140 character message - you have to create `author` first thru `addAuthor` mutation.

### Wall
* A user should be able to see a list of the messages they've posted, in reverse chronological order. 

### Following
* A user should be able to follow another user. 

### Timeline
A user should be able to see a list of the messages posted by all the people they follow, in reverse chronological order.

## GraphQL API
* some examples of queries can be found in `resources/queries` folder.

### Example queries
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
```

* adding authors
```gql
mutation appendData {
  addAuthor(name: "author-name") {
    id
    name
  }
}
```

* getting author related things
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
}
```

### Example mutation
* adding author
```gql
mutation addAuthor {
  addAuthor(name: "test") {
    id
    name
  }
}
```

* adding tweet
```gql
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
* go to playground endpoint `http://localhost:9000/graphiql` or use alternative tool to query `graphql` - [`insomnia`](https://insomnia.rest/graphql/)

#### Running tests
* `./gradlew test`

#### TODO
* improve tests
* improve docs

### Maybe maybe
* better semantics https://github.com/palatable/lambda
* easier to follow https://github.com/google/rejoiner