[![Build Status](https://travis-ci.org/Hyunk3l/user-api.svg?branch=master)](https://travis-ci.org/Hyunk3l/user-api)

# User Api

## How to run
```
./gradlew bootRun
```

## Endpoints

1. Get list of users
```
GET http://localhost:8000/users
```

2. Create a new user
```
curl -XPOST http://127.0.0.1:8000/users \
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

Using **Kluent** that is a Kluent is a ?Fluent Assertions? library written specifically for Kotlin [https://markusamshove.github.io/Kluent/](https://markusamshove.github.io/Kluent/).

Run tests:
```
./gradlew test
```