package com.training.victor.development.utils

import com.training.victor.development.network.responses.LoginResponse


fun createMockedLoginResponseExpired(): LoginResponse {
    return LoginResponse(
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhbmRyb2lkQ2hhbGxlbmdlQHZpdnkuY29tIiwic3R5cGUiOiJVU0VSIiwic2NvcGUiOlsiYmFzaWMiXSwiaWQiOiJlY2I3NDBlNS0wNzMxLTQ3ZWEtODAyNC03YzFjYTlhZWQzMjciLCJleHAiOjE1NDI4NzQzMTYsImp0aSI6IjU3MTBjZGM5LTJlMTAtNGQyNy1hNTM2LTk3YWNjMTQ3MWVlOCIsImNsaWVudF9pZCI6ImlwaG9uZSJ9.-7zK6RuujenOuIU3X2eLdZscOjJI_F4jlqV0YaxrmDs",
        "bearer",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhbmRyb2lkQ2hhbGxlbmdlQHZpdnkuY29tIiwic3R5cGUiOiJVU0VSIiwic2NvcGUiOlsiYmFzaWMiXSwiYXRpIjoiYTU0Njc4ZTMtZWZiZS00OTNlLWJmNjItNDgyMTRjYzc2YWNkIiwiaWQiOiJlY2I3NDBlNS0wNzMxLTQ3ZWEtODAyNC03YzFjYTlhZWQzMjciLCJleHAiOjE1NDU0NjQxNTcsImp0aSI6IjMwOTAwYTg2LTU2MWQtNGIwNC1iZDJlLWUxMzRlNTk0ZDZjZiIsImNsaWVudF9pZCI6ImlwaG9uZSJ9.dTiVkYxK5LgcLpyjzjMftLw7FCT66tYH-M7hsVIg_pw",
        299,
        "scope",
        "jti",
        false
        )
}