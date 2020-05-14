<template>
  <div id="welcome">
          <!--Navigation bar to navigate between vue components-->
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item id="login" v-on:click="gotologin" v-if="!isHidden">Login</b-nav-item>
        <b-nav-item id="profile" v-on:click="gotoprofile" v-if="isHidden">Profile</b-nav-item>
        <b-nav-item id="logout" v-on:click="logout" v-if="isHidden">Logout</b-nav-item>
        <b-nav-item v-on:click="gotohome">Home</b-nav-item>
        <b-form-input
          type="search"
          v-model="searchWord"
          id="searchWord"
          name="searchWord"
          class="mr-sm-2"
          placeholder="Search"
        ></b-form-input>
        <b-button
          variant="outline-success"
          class="my-2 my-sm-0"
          type="submit"
          v-on:click="search(searchWord)"
        >Search</b-button>
      </b-navbar-nav>
    </b-navbar>
          <!--Navigation bar ends here-->
    <br />
    <!--AAAAAAAASO TUTOR SERVICESSSS 41-->
    <h1>Welcome to the ASO Tutorial Service!</h1>
    <br />
    <div>
  <b-button variant="outline-success" v-on:click="starter" v-if="!isHidden">Let's Get Started!</b-button>
</div>
    <b-container v-if="isSearch">
      <b-table hover :items="result" :fields="fields" caption-top responsive="sm">
        <template v-slot:table-caption>Search Results ({{result.length}})</template>
        <template v-slot:cell(find_tutors)="row">
          <b-button
            variant="outline-success"
            @click="row.toggleDetails"
            class="my-2 my-sm-0"
          >Find Available Tutors</b-button>
        </template>
        <template v-slot:row-details="row">
          <b-card>
		  <b-row>
              <b-col><h4>Murat Polat</h4></b-col>
			  <b-col><h4>4.5/5.0</h4></b-col>
			  <b-col><h4>$25</h4></b-col>
              <b-col>
                <b-button
                  variant="outline-success"
                  type="submit"
                  @click="gotobookingform"
                >Book a Session!</b-button>
              </b-col>
            </b-row>
			<br>
			<b-row>
				<b-col><h4>Onur Cayci</h4></b-col>
				<b-col><h4>4.1/5.0</h4></b-col>
			  <b-col><h4>$20</h4></b-col>
              <b-col>
                <b-button
                  variant="outline-success"
                  type="submit"
                  @click="gotobookingform"
                >Book a Session!</b-button>
				 </b-col>
            </b-row>
          </b-card>
        </template>
      </b-table>
      <b-button
        variant="outline-success"
        type="submit"
        class="my-2 my-sm-0"
        v-on:click="gotorequestcourse"
      >Cannot find the course you are looking for? Request The Course Here!</b-button>
    </b-container>
    <img
      src="../assets/tutoring.jpeg"
      alt="Welcome page"
      width="100%"
      height="100%"
      v-if="!isSearch && isHidden"
    />
    <br />
    <br />
  </div>
</template>

<script>
//initialization of axios for integration methods
import axios from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
var currentUser = null;
var firstname = null;
var isHidden = false;
var isLog = false;
var AXIOS = axios.create({
  baseURL: backendUrl
});

export default {
  name: "welcome",

  data() {
    return {
      searchWord: null,
      currentUser: null,
      isHidden: false,
      isSearch: false,
      isFindTutor: false,
      firstname: null,
      fields: ["code", "name", "institution", "find_tutors"],
      tutorFields: ["tutor_name", "book_session"]
    };
  },

  methods: {
    //gets the profile to check if we're logged-in, will return null if no one is logged in
    starter: function() {
    AXIOS.get("/profile")
    .then(response => {
      console.log(response.data);
      currentUser = response.data;
      if (response.data.length == 0) {
        window.alert("You're not logged in! You can use the Search bar!");
        window.location.href = "/";

      }
      firstname = response.data.name;
      this.isHidden = true;
    })
    .catch(e => {
      window.alert(e);
    });    },
    gotohome: function() {
      window.location.href = "/";
    },
    gotologin: function() {
      AXIOS.get("/profile")
      .then(response => {
        console.log(response.data);
        currentUser = response.data;
      });
      //hides log-in button if we're already logged in
      if (currentUser != null) {
        this.isHidden = true;
      } else {
        this.isHidden = false;
        window.location.href = "/#/login";
      }
    },
    //goes to the profile page
    gotoprofile: function() {
      window.location.href = "/#/profile";
    },
    //goes to the request course page
    gotorequestcourse: function() {
      window.location.href = "/#/requestCourse";
    },
    gotobookingform: function() {
      window.location.href = "/#/bookingForm";
    },
    //searches for courses.
    search: function(searchWord) {
      if (this.searchWord == null || this.searchWord == "") {
        //gets all the courses
        AXIOS.get("/courses")
          .then(response => {
            this.result = response.data;
            this.searchWord = null;
            if (this.result != null) {
              this.isSearch = true;
              console.log(this.result);
            }
          })
          .catch(e => {
            window.alert(e);
          });
      } else {

        AXIOS.post("/courses/search/", {
          name : searchWord
        }, {})
          .then(response => {
            this.result = response.data;
            console.log(this.result);
            this.searchWord = null;
            if (this.result != null) {
              this.isSearch = true;
            }
          })
          .catch(e => {
            window.alert(e);
          });
      }
    },
    logout: function() {
      AXIOS.post("/logout")
        .then(response => {
          window.location.href = "/";
        })
        .catch(e => {
          window.alert(e);
        });
    }
  }
};
</script>

<style>
.button {
  background-color: #4caf50; /* Green */
  border: none;
  color: white;
  padding: 5px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
}
</style>
