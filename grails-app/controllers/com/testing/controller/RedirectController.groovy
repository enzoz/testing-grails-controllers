package com.testing.controller

import grails.converters.*

class RedirectController {

	def redirectUrl = {
		render "Nice be redirected."
	}
}
