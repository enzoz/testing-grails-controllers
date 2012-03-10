package com.testing.controller

import grails.converters.*

class HomeController {

	/**
	* It's called as default method from a Grails controller.
	* Its behavior is to awnser for '/' calls, like http://you.app.com/yourcontext/yourcontroller.
	*/
	def index = {
		render "Hello world.  Lets test some controller methods."
	}

    def greeting = { 
		def appended = "${params.greeting} ${params.name}"
		render appended
	}
	
	/**
	* This one here generate a default XML provided by grails converters, it's kinda ugly.
	* Strongly recommended generate your own XML by hand or via XmlSlurper class.
	*/
	def renderMapAsXml = {
		def myXml = [tags:[contents:["Hello world!", "It was rendered as XML"]]]
		render myXml as XML
	}

	def renderCustomXml = {
		render(contentType:"text/xml", encode:"UTF-8"){
			tags(){
				content("Hello world!")
				content("It was rendered as XML")
			}
		}
	}
	
	def renderMapAsJson = {
		def myJson = [tags:[contents:["Hello world!", "It was rendered as JSON"]]]
		render myJson as JSON
	}
	
	def renderTemplate = {
		def modelMap = [:]
		modelMap.name = params.name
		render(template:"/home/renderingGSP", contentType:"text/html", model:modelMap)
	}
		
	def redirectToUri = {
		redirect(url:"http://google.com")
	}
	
	def redirectToAnotherController = {
		redirect(controller:"redirect", action:"redirectUrl")
	}
}
