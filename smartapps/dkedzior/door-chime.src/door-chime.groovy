/**
 *  Door Chime
 *
 *  Copyright 2017 Dave Kedzior
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Door Chime",
    namespace: "dkedzior",
    author: "Dave Kedzior",
    description: "Plays sound (chime) when contact sensor opens or closes.",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("Sirens"){
		input "sirens", "capability.alarm", title: "Which?", required: true, multiple: true
	}
	section("Virtual Switch"){
		input "dswitch", "capability.switch", title: "Which?", required: true, multiple: false
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	initialize()
}

def initialize() {
    subscribe(dswitch, "switch.on", switchHandler)
}

def switchHandler(evt) {
    sirens?.siren()
  	dswitch.off()
    sirens?.off()
}