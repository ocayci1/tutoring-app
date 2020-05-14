<template>
    <div id="bookingForm">
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
    <!--Booking a tutor session form, where we get inputs of student email, tutor name, course, date, time, and duration. WHen the button is
    clicked, we post an AXIOS request to the REST API controller to create a booking object.-->
	  <h1>Book Session</h1>
			  <br>
			  <img src="../assets/Booking.jpg" alt="Course not found" width=200 height=200>
		      <br>
			  <label label-for="email">Student Email:</label>
              <br>
              <b-input class="textbox" type="email" id="email" name="email" v-model="email" placeholder="example@email.com"></b-input>
              <br>
              <label label-for="name">Tutor Name:</label>
              <br>
              <b-input class="textbox" type="text" id="name" name="name" v-model="name" placeholder="John Doe"></b-input>
              <br>
			  <label label-for="roomCode">Room:</label>
              <br>
              <b-form-select class="textbox" v-model="selected" :options="options" :select-size="4"></b-form-select>
			  <div class="mt-3">Selected: <strong>{{ selected }}</strong></div>
              <br>
              <label label-for="courseName">Course Name:</label>
              <br>
              <b-input class="textbox" type="text" id="courseName" name="courseName" v-model="courseName" placeholder="i.e. Calculus"></b-input>
              <br>
              <label label-for="duration">Duration (in minutes):</label>
              <br>
              <b-input class="textbox" type="number" id="duration" name="duration" v-model="duration" placeholder="i.e. 60"></b-input>
			  <br>
			  <label label-for="date">Date:</label>
              <br>
              <b-input class="textbox" type="date" id="date" name="date" v-model="date" placeholder="yyyy-mm-dd"></b-input>
			  <br>
			  <label label-for="startTime">Start Time:</label>
              <br>
              <b-input class="textbox" type="time" id="startTime" name="startTime" v-model="startTime" placeholder="00:00"></b-input>
			  <br>

              <button class="button" type="submit" variant="primary" v-on:click="submitbooking(email,name,roomCode,courseName,duration,date,startTime)">Submit</button>
	  </div>
</template>

<script>
//creating AXIOS variable in order to make calls to the backend
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var currentUser = null;
var roomCode = null;

var AXIOS = axios.create({
  baseURL: backendUrl,
})

//a function to get the information about the user that is currently logged in. This info is assigned to a variable on the backend
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
  name: 'bookingForm',

  data () {
    return {
      roomCode: null,
      isHidden: null,
      currentUser: null,
      selected: null,
      email: null,
      name: null,
      courseName: null,
      duration: null,
      date: null,
      startTime: null,
      options: [
        { value: null, text: ' ', disabled: true },
        { value: 'R1', text: 'R1' },
        { value: 'R2', text: 'R2' },
        { value: 'R3', text: 'R3' },
        { value: 'R4', text: 'R4', disabled: true },
        { value: 'R5', text: 'R5' },
        { value: 'R6', text: 'R6' }
      ],
      tutors: [
        {name: "Marwan Kanaan", course: {course_code: "ECSE 321", couse_name: "Intro to Software Engineering", institution: "McGill University"}},
        {name: "Melis Malki", course: [
          {course_code: "MATH 133", course_name: "Linear Algebra and Geometry", institution: "McGill University"},
          {course_code: "MATH 141", course_name: "Calculus II", institution: "McGill University"},
          {course_code: "MATH 262", course_name: "Intermediate Calculus", institution: "McGill University"}
          ]},
        {name: "Onur Cayci", course: {course_code: "ECSE 202", course_name: "Intro to Software Programming", institution: "McGill University"}},
      ],
      courses: [
        {course_code: "ECSE 321", couse_name: "Intro to Software Engineering", institution: "McGill University"},
        {course_code: "MATH 133", course_name: "Linear Algebra and Geometry", institution: "McGill University"},
        {course_code: "MATH 141", course_name: "Calculus II", institution: "McGill University"},
        {course_code: "MATH 262", course_name: "Intermediate Calculus", institution: "McGill University"},
        {course_code: "ECSE 202", course_name: "Intro to Software Programming", institution: "McGill University"}
      ]
    }
  },
  created: function () {
    this.currentUser = getUser();
    if(this.currentUser != null) {
      this.isHidden = true;
    }
  },
  methods: {
    //function to re-direct the page to the home page
    gotohome: function() {
      window.location.href = '/'
    },
    //function to re-direct the page to the login page
    gotologin: function() {
      window.location.href = '/#/login'
    },
    //function to re-direct the page to the profile page
    gotoprofile: function() {
      window.location.href = '/#/profile'
    },
    //logout function which sets the current User variable to null, which will prevent the user to access some of the functionalities
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
    //this is the function that posts a backend call to create a booking.
    submitbooking: function(email,name,roomCode,courseName,duration,date,startTime){
    AXIOS
    .post('/booking/create', {
      email: email,
      name: name,
      roomCode: roomCode,
      courseName: courseName,
      duration: duration,
      date: date,
      startTime: startTime
    }, {})
    .then((response) => {
      window.alert("Congratulations! Your booking is submitted. Date: " + date + " Time: " + time);
      window.location.href = '/'
    }, (error) => {
      console.log(error);
    });
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
  width: 50%;
  height: 30px;
  margin-right: auto;
  margin-left: auto;
 }
</style>
