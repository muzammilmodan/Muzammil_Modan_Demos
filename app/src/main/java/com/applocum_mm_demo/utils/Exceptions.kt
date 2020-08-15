package com.applocum_mm_demo.utils

import java.io.IOError
import java.io.IOException

//Todo:After api call get Any Api exception
class ApiExceptions (message : String) : IOException(message)

class ApiUnauthorizedExceptions (message : String) : IOException(message)

class NoInternetException(message: String) : IOException(message)