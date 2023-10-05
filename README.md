<h1 align="center" style="border-bottom: none;">🚀Simple People API </h1>
<h3 align="center">This a simple People API made with Java SpringBoot.</h3>

## Endpoints 
1. People:
    - GET
        - /api/v1/people -> listAll
        - /api/v1/people/{id} -> show
   - Post
     -  /api/v1/people/save -> Insert
   ```JSON
      {
         "name": "Name Test",
          "birthdayDate": "2000-12-12",
          "address": []
      }
    ```
    - PUT
     -  /api/v1/people/update/{id}
   ```JSON
      {
         "name": "New Name Test",
          "birthdayDate": "2000-12-12",
          "address": []
      }
    ```
2. Address:
    - GET
        - /api/v1/address/{peopleId} -> show
   - Post
     -  /api/v1/address/save -> Insert
   ```JSON
        {
      "number": 160,
      "zipCode": 999999-999,
      "publicPlace": " Test Public Place",
      "city": " City Test",
      "main": false
      }
    ```
      - PUT
           -  /api/v1/address/update/{addressId}
```JSON
      {
      "number": 160,
      "zipCode": 999999-999,
      "publicPlace": "New Test Public Place",
      "city": "New City Test",
      "main": true
      }
```

    
