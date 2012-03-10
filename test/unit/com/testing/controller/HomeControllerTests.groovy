package com.testing.controller

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import grails.test.*
import grails.converters.*
import org.custommonkey.xmlunit.*

class HomeControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGreeting() {
    	def expectedResponse = "Hello world!"
		controller.params.greeting ='Hello'
    	controller.params.name = 'world!'
    	
    	controller.greeting()
    	def responseString = controller.response.contentAsString

    	assertThat responseString, is(expectedResponse)
    }

    void testRenderMapAsXML(){
    	def expectedValues = [tags:[contents:["Hello world!", "It was rendered as XML"]]] as XML
		controller.renderMapAsXml()
		
		def responseContent = controller.response.contentAsString
		def xmlDiff = new Diff(expectedValues.toString(), responseContent)
		assert xmlDiff.similar()
    }

    void testRenderMyOwnXML(){
		controller.renderCustomXml()

		def responseContent = controller.response.contentAsString
		def responseXml = new XmlSlurper().parseText(responseContent)
		
		def contents = responseXml.children().collect{ it.text() }
		
		assertThat contents, is(["Hello world!", "It was rendered as XML"])
    }

    void testeRenderJson(){
    	controller.renderMapAsJson()

    	def responseJson = JSON.parse(controller.response.contentAsString)
    	def tags = responseJson.tags

    	assertThat tags.contents, is(["Hello world!", "It was rendered as JSON"])
    }
	
	void testRenderTemplate(){
		controller.params.name="a name"
		controller.renderTemplate()
		
		def renderArgs = controller.renderArgs
		assertThat renderArgs.template, is("/home/renderingGSP")
		assertThat renderArgs.model, is([name:"a name"])
	}
	
	void testRedirectToUrl(){
		controller.redirectToUri()
		
		def redirectArgs = controller.redirectArgs
		assertThat redirectArgs.url, is("http://google.com")
	}
	
	void testRedirectToAnotherController(){
		controller.redirectToAnotherController()
		
		def redirectArgs = controller.redirectArgs
		assertThat redirectArgs.controller, is("redirect")
		assertThat redirectArgs.action, is("redirectUrl")
	}
}
