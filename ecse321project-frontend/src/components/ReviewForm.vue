<template>
    <div id="reviewForm">
            <!--Navigation bar to navigate between vue components-->
      <b-navbar type="dark" variant="dark">
        <b-navbar-nav>
          <b-nav-item id="profile" v-on:click="gotoprofile">Profile</b-nav-item>
          <b-nav-item id="logout" v-on:click="logout">Logout</b-nav-item>
          <b-nav-item v-on:click="gotohome">Home</b-nav-item>
        </b-navbar-nav>
      </b-navbar>
            <!--Navigation bar ends here-->
	  <br>
	  <h1>Review Booking Session</h1>
	  <b-container class="bv-example-row">
      <b-row>
          <b-col>
			  <br>
			  <label  label-for="email"><b>Student Email:</b></label>
			  <br>
			  <b-input class="emailbox" type="email" id="email" name="email" v-model="email" placeholder="example@email.com"></b-input>
              <br>

              <label label-for="name"><b>Tutor Name:</b></label>
              <br>
              <b-input class="emailbox" type="text" id="name" name="name" v-model="name" placeholder="John Doe"></b-input>
              <br>
			  <label label-for="date"><b>Date of Session:</b></label>
              <br>
              <b-input class="emailbox" type="date" id="date" name="date" v-model="date" placeholder="yyyy-mm-dd"></b-input>
      </b-col>
		</b-row>
		</b-container>
			<br>
			<label  label-for="name"><b>Rate:</b></label>
			  <br>
			  <b-form-select class="ratebox" v-model="selected" :options="options" :select-size="1"></b-form-select>
			  <div class="mt-3">Selected: <strong>{{ selected }}</strong></div>
			  <br>
			<label  label-for="email"><b>Review:</b></label>
			  <br>
              <b-input class="textbox" type="text" id="comment" name="comment" v-model="comment" placeholder="Please write your feedback here."></b-input>

		<br>
              <button class="button" type="submit" variant="primary" v-on:click="review">Submit</button>
	  </div>
</template>

<script>
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var currentUser = null;

var AXIOS = axios.create({
  baseURL: backendUrl,
})
//gets the current user from the backend
function getUser() {
  AXIOS.get('/profile')
  .then(response => {
    this.currentUser = response.data;
  })
  .catch(e => {
    window.alert(e);
  })
}

export default {
    name: 'reviewForm',

    data () {
      return {
        currentUser: null,
        isHidden: false,
        name: null,
        email: null,
        date: null,
        comment: null,
        rate: null,
        selected: null,
        selected1: null,
        options: [
              { value: null, text: ' ', disabled: true },
              { value: '1', text: '1' },
              { value: '2', text: '2' },
              { value: '3', text: '3' },
              { value: '4', text: '4' },
              { value: '5', text: '5' },
            ],
        options1: [
              { value: null, text: ' ', disabled: true },
              { value: 'true', text:'Tutor'  },
          { value: 'false', text:'Student' },
        ]
      }
    },
    methods: {
      //methods that allow the user to go to other pages using the top bar
      gotohome: function() { 
        window.location.href = '/'
      },
      gotoprofile: function() {
		    window.location.href= '/#/profile/'
	    },
         logout: function() {
      AXIOS.post("/logout")
        .then(response => {
          window.location.href = "/";
        })
        .catch(e => {
          window.alert(e);
        });
    },
	  review: function () {
		window.location.href='/#/profile/'
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
.emailbox{
  padding: 10px 20px;
  width: 80%;
  height: 30px;
  margin-right: auto;
  margin-left: auto;
 }
.textbox{
  width: 80%;
  height: 80px;
  margin-right: auto;
  margin-left: auto;
 }
 .ratebox{
  padding: 10px 20px;
  width: 35%;
  height: 5px;
  margin-right: auto;
  margin-left: auto;
 }


</style>
