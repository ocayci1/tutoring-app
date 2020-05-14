<template>
  <div id="requestCourse">
    <!--a page that allows the user to request a course if it is not apparent in our search feature , or they cannot find the course they are looking
    for-->
      <!--Navigation bar to navigate between vue components-->
      <b-navbar type="dark" variant="dark">
        <b-navbar-nav>
          <b-nav-item id="login" v-on:click="gotologin" v-if="!isHidden">Login</b-nav-item>
          <b-nav-item id="profile" v-on:click="gotoprofile" v-if="isHidden">Profile</b-nav-item>
          <b-nav-item id="logout" v-on:click="logout" v-if="isHidden">Logout</b-nav-item>
          <b-nav-item v-on:click="gotohome">Home</b-nav-item>
        </b-navbar-nav>
      </b-navbar>
      <!--Navigation bar ends here-->
	  <br>
	  <h1>Course not found!</h1>
	  <br>
	  <img src="../assets/request.png" alt="Course not found" width=200 height=200>
	  <br>
	  <h2>Make sure all words are spelled correctly.</h2>
	  <h4> or... </h4>
	  <br>
	  <h2> It is possible that this course is currently not offered.</h2>
	  <br>
	  <br>
	  <h2>Help us to get better!</h2>
	  <br>
	  <button class="button" v-on:click="gotorequestcourseform">Request Course</button>
	  <br>
	  <br>
   </div>
</template>

<script>
//set-up for axios
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var currentUser = null;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function getUser() {
  //gets the current user from the backend
  AXIOS.get('/profile')
  .then(response => {
    return response.data;
  })
  .catch(e => {
    window.alert(e);
  })
}

export default {
    name: 'requestCourse',

    data () {
      return {
        searchWord: null,
        currentUser: null,
        result: null,
        isHidden: false,
      }
    },
    created: function () {
      this.currentUser = getUser();
      if(this.currentUser != null) {
        this.isHidden = true;
      }
    },
    methods: {
      //top bar functions
      gotohome: function() {
        window.location.href = '/'
      },
      gotologin: function() {
        window.location.href = '/#/login'
      },
      gotoprofile: function() {
        window.location.href = '/#/profile'
      },
      gotorequestcourse: function() {
        window.location.href = '/#/requestCourse'
      },
      //search function, gets all courses with the typed key word
      search: async function(searchWord) {
        if(searchWord == null) {
          await AXIOS.get('/courses')
          .then(response => {
            result = response.data
          })
        }
        else {
          await AXIOS.get('')
        }
      },
      //logs out of the system, sets the current User to null
      logout: function() {
        AXIOS.get('/logout')
        .then(response => {
          this.currentUser = null;
          window.location.href = '/'
        })
        .catch(e => {
          window.alert(e);
        })
      },
	  gotorequestcourseform: function(){
	  window.location.href = '/#/requestcourseform'
	  }
    }
}
</script>

<style>
.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 5px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}
</style>
