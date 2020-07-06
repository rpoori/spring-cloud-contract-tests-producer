package user

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/api/user/active/MILLENIAL'
//        headers {
//            header(authorization(), 'Bearer token')
//        }
    }
    response {
        status 200
        body([
                [
                        "id": "user-id-1",
                        "name": "user-name-1",
                        "email": "user-email-1@test.com",
                        "status": "ACTIVE"
                ],
                [
                        "id": "user-id-2",
                        "name": "user-name-2",
                        "email": "user-email-2@test.com",
                        "status": "ACTIVE"
                ],
                [
                        "id": "user-id-3",
                        "name": "user-name-3",
                        "email": "user-email-3@test.com",
                        "status": "ACTIVE"
                ]
        ])
    }
}
