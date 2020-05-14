<template>
    <div id="requestCourseForm">
         <!--the form that allows the user to request a course that is not currently offered by the ASO TUTOR COMPANY-->
      <b-navbar type="dark" variant="dark">
        <b-navbar-nav>
                <!--Navigation bar to navigate between vue components-->
          <b-nav-item id="login" v-on:click="gotologin" v-if="!isHidden">Login</b-nav-item>
          <b-nav-item id="profile" v-on:click="gotoprofile" v-if="isHidden">Profile</b-nav-item>
          <b-nav-item id="logout" v-on:click="logout" v-if="isHidden">Logout</b-nav-item>
          <b-nav-item v-on:click="gotohome">Home</b-nav-item>
        </b-navbar-nav>
      </b-navbar>
            <!--Navigation bar ends here-->

	  <br>
	  <h1>Request Course Form</h1>
		      <b-label  label-for="name">Course Name:</b-label>
              <br>
              <b-input class="textbox" type="text" id="name" name="name" v-model="name" placeholder="i.e. Calculus"></b-input>
              <br>
              <b-label label-for="code">Course Code:</b-label>
              <br>
              <b-input class="textbox" type="text" id="code" name="code" v-model="code" placeholder="i.e. MATH141"></b-input>
              <br>
              <b-label label-for="institution">Institution Name:</b-label>
              <br>
              <b-input class="textbox" type="text" id="institution" name="institution" v-model="institution" placeholder="i.e. McGill University"></b-input>
              <br>
              <b-label label-for="faculty">Faculty:</b-label>
              <br>
              <b-input class="textbox" type="text" id="faculty" name="faculty" v-model="faculty" placeholder="i.e. Engineering"></b-input>
			  <br>
			  <b-label label-for="level">Level:</b-label>
              <br>
              <b-input class="textbox" type="text" id="level" name="level" v-model="level" placeholder="i.e. Bachelor"></b-input>
			  <br>
              <button class="button" type="submit" variant="primary" v-on:click="gotohome">Submit</button>
	  </div>
</template>

<script>
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var currentUser = null

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
//gets the current user from the back end
function getUser() {
  AXIOS.get('/profile')
  .then(response => {
    return response.data;
  })
  .catch(e => {
    window.alert(e);
  })
}

export default {
    name: 'requestCourseForm',

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
      if(currentUser != null) {
        this.isHidden = true;
      }
    },
    methods: {
      //methods that allow the user to go to other pages using the top bar
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
      //logout function, sets the current user as null
      logout: function() {
        AXIOS.get('/logout')
        .then(response => {
          this.currentUser = null;
          window.location.href = '/'
        })
        .catch(e => {
          window.alert(e);
        })
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
.textbox{
  padding: 10px 20px;
  width: 30%;
  height: 30px;
  margin-right: auto;
  margin-left: auto;
 }

</style>
