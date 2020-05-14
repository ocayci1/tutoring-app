<template>
  <div id="studentProfile">
    <!--Navigation bar to navigate between vue components-->
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item id="logout" v-on:click="logout">Logout</b-nav-item>
        <b-nav-item v-on:click="gotohome">Home</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
    <!--Navigation bar ends here-->
    <br>
    <div id="studentProfile">
    <!--this part is used to display current user's name and rating-->
	  <b-button variant="outline-success" v-on:click="starter" v-if="!isHidden">Proceed to Profile!</b-button>
      <h2>{{currentUser.name}}</h2>
      <h4>{{currentUser.rating}}</h4>
      <b-container>
		  <b-row>
		  <b-col>
			<br>
			<br>
      <h2 v-if="isHidden">Reviews</h2>
			<hr v-if="isHidden">
			<h4 v-if="isHidden"><u>Murat Polat</u></h4>
			<h4 v-if="isHidden"><i>"Very Good Listener"</i></h4>
          </b-col>
        </b-row>
		<b-row>
          <b-col>
			<br>
			<br>
      <b-button type="submit" variant="outline-success" v-if="isHidden" @click="viewBookings">View Bookings</b-button>
      </b-col>
      </b-row>
      </b-container>
      <b-container>
        <!--this section uses a table to display all of the bookings of the user, current and past
          this section also adds the ability to cancel current bookings as well-->
      <b-table :items="bookings" :fields="fields" caption-top v-if="bookingToggle">
        <template v-slot:table-caption>Bookings</template>
        <template v-slot:cell(write_review)="row">
          <b-button type="submit" variant="outline-success" @click="gotoreviewform">Write a Review</b-button>
        </template>
        <template v-slot:cell(cancel)="row">
          <b-button type="submit" variant="outline-success" @click="cancelbooking(row.item)">Cancel Booking</b-button>
        </template>
      </b-table>
      <br>
      </b-container>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var isHidden = false;
var currentUser = null;
var userEmail = null;
var bookingToggle = null;
var reviewToggle = null;
var name = null;
var bookings;

var AXIOS = axios.create({
  baseURL: backendUrl,
})

export default {
  name: 'profile',

  data() {
    return {
      currentUser: null,
	  name: null,
      userEmail: null,
	  profileFields: ["name", "rate"],
      fields: ["course.name", "date", "write_review", "cancel"],
      reviewFields: ["tutor", "review"],
      bookingToggle: false,
	  isHidden: false,
      reviewToggle: false,
      bookings: [],
      reviews: [],
    }
  },
  //this helps us to get the user data when the page is loaded, so that we can display relevant information about the user
  created: function() {
    AXIOS.get('/profile')
    .then(response => {
      if(response.data == null) {
        window.location.href = '/'
      }
    })
    .catch(e => {
      window.alert("You're not logged in!");
    })
  },
  methods: {
    //this is a button just in case the created method does not work, they do the same thing, get the user's (student) data from the database
    //and use it to display bookings, name and rating
    starter: function() {
      AXIOS.get("/profile")
      .then(response => {
        console.log(response.data);
        name = response.data.name;
        if (response.data.length == 0) {
          window.alert("You're not logged in! You can use the Search bar!");
          window.location.href = "/";

        }
        name = response.data.name;
        this.isHidden = true;
      })
      .catch(e => {
        window.alert(e);
      });
    },
    //function to redirect the page to the home page
    gotohome: function() {
      window.location.href = '/'
    },
    //function to set the currentUser variable to null which allows to hide some of the functionality from the user since we do not have access
    //to their student object from the database
    logout: function() {
      AXIOS.post('/logout')
      .then(response => {
        response.data = currentUser;
        window.location.href = '/'
      })
      .catch(e => {
        window.alert(e);
      })
    },
    //this is a helper method to get the bookings of the currentUser's from the booking just in case the returned student object json
    //does not include that list
    viewBookings: function() {
      AXIOS.get('/profile').then(response => {this.userEmail = response.data.email}).catch(e => {window.alert(e)});
     // AXIOS.get('/student/'+ this.userEmail +'/bookings').then(response => {this.bookings = response.data; this.bookings = [{"name": "ASO"},{"name": "HODRI MEYDAN"}] ; this.bookingToggle = !this.bookingToggle}).catch(e => {window.alert(e)});
          AXIOS.get('/student/'+ this.userEmail +'/bookings').then(response => {this.bookings = response.data; console.log(bookings); this.bookingToggle = !this.bookingToggle}).catch(e => {window.alert(e)});

    },
    viewReviews: function() {
      this.reviews = [];
      this.reviewToggle = !this.reviewToggle;
    },
    //a function to redirect the user to the review for page in case of they want to write a review about their experience with the tutor
    gotoreviewform: function() {
      window.location.href = "/#/reviewForm"
    },
    //a function to cancel an upcoming booking
    cancelbooking: function(item) {
      AXIOS.get('/booking/cancel/')
      .then(response => {
        console.log(response.data);
      })
      .catch(e => {
        window.alert(e);
      })
    }
  }
}
</script>
