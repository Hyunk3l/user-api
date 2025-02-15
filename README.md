[![Build Status](https://travis-ci.org/Hyunk3l/user-api.svg?branch=master)](https://travis-ci.org/Hyunk3l/user-api)

# User Api

## Requirements
- Java 17
- Gradle 8.x

## How to run
```
./gradlew bootRun
```

## Endpoints

1. Get list of users
```
GET http://localhost:8000/v1/users
```

2. Create a new user
```
curl -XPOST http://127.0.0.1:8000/v1/users \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "Fabrizio",
	"surname": "Di Napoli",
	"email": "your-mail@gmail.com",
	"password": "123456"
}'
```

you'll get something like:
```
{
    "id": "6fbf4ef5-d84b-4dcd-b227-3bce550ba8db"
}
```

## Testing

Run tests:
```
./gradlew test
```

## Libraries
* [AssertJ](http://joel-costigliola.github.io/assertj/)
* [JSONAssert](https://github.com/skyscreamer/JSONassert)
* Spring Boot 3.1.5
* Kotlin 1.8.21

## Other links
* [JSONAssert examples](https://www.baeldung.com/jsonassert)
* [My blog](https://www.fabridinapoli.com)