<template>
  <div id="login">
    <!--Navigation bar to navigate between vue components-->
    <b-navbar type="dark" variant="dark">
      <b-navbar-nav>
        <b-nav-item v-on:click="gotohome">Home</b-nav-item>
      </b-navbar-nav>
    </b-navbar>
    <!--Navigation bar ends here-->
    <br />
    <b-container class="bv-example-row">
      <b-row>
        <b-col>
          <h1>Login</h1>
          <label label-for="loginEmail">Email Address:</label>
          <br />
          <b-input
            type="email"
            name="loginEmail"
            id="loginEmail"
            v-model="loginEmail"
            placeholder="example@email.com"
          ></b-input>
          <br />
          <label label-for="loginPassword">Password:</label>
          <br />
          <b-input
            type="password"
            name="loginPassword"
            id="loginPassword"
            v-model="loginPassword"
          ></b-input>
          <br />
          <b-button
            type="submit"
            variant="outline-success"
            v-on:click="login(loginEmail, loginPassword)"
            >Login</b-button
          >
        </b-col>
        <b-col>
          <h1>Sign Up</h1>
          <label label-for="name">Your Full Name:</label>
          <br />
          <b-input
            type="text"
            id="name"
            name="name"
            v-model="name"
            placeholder="John Doe"
          ></b-input>
          <br />
          <label label-for="email">Email Address:</label>
          <br />
          <b-input
            type="email"
            id="email"
            name="email"
            v-model="email"
            placeholder="example@email.com"
          ></b-input>
          <br />
          <label label-for="password">Password:</label>
          <br />
          <b-input
            type="password"
            id="password"
            name="password"
            v-model="password"
          ></b-input>
          <br />
          <label label-for="confirmPassword">Confirm Password:</label>
          <br />
          <b-input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            v-model="confirmPassword"
          ></b-input>
          <br />
          <b-button
            type="submit"
            variant="outline-success"
            v-on:click="signup(name, email, password)"
            >Sign Up</b-button
          >
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
//creating an axios event to interact with the backend REST controller
import axios from "axios";
var config = require("../../config");
var configtwo = {
  headers: { "Content-Type": undefined }
};
var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl
});

export default {
  name: "login",

  data() {
    return {
      loginEmail: null,
      loginPassword: null,
      name: null,
      email: null,
      password: null,
      confirmPassword: null,
      student: null
    };
  },

  methods: {
    //function to redirect the current page to the home page
    gotohome: function() {
      window.location.href = "/";
    },
    //login function, which gets the student object for the given email and password, which is used for profile and past booking information
    login: function(loginEmail, loginPassword) {
      //if an input is missing we alert the window and do not send any info to the backend
      if (loginEmail == null || loginPassword == null) {
        window.alert("Please enter your Email/Password");
        this.loginEmail = null;
        this.loginPassword = null;
      } else {
        //else we send the entered info to the backend and find the corresponding student object that has the same email and password
        AXIOS.post(
          "/student",
          {
            email: loginEmail,
            password: loginPassword
          },
          {}
        )
          .then(response => {
            //if an empty data is returned, we assume that there is no such student so we assume their email or password is wrong
            //this can also notify the user try to login to sign up if they do not have an account
            if (response.data.length == 0) {
              window.alert("Email or Password does not match");
              this.loginEmail = null;
              this.loginPassword = null;
            } else {
              this.student = response.data;
              this.loginEmail = null;
              this.loginPassword = null;
              window.location.href = "/";
            }
          })
          .catch(e => {
            window.alert(e);
            this.loginEmail = null;
            this.loginPassword = null;
          });
      }
    },
    //signup function that creates a new student objects in the backend with the given name, email, and password
    signup: function(name, email, password) {
      const params = new URLSearchParams();
      params.append("name", name);
      params.append("email", email);
      params.append("password", password);
      //if any field is empty, we alert the window saying we need the info
      if (
        name == null ||
        email == null ||
        password == null ||
        confirmPassword == null
      ) {
        window.alert("Cannot sign up because of empty fields!");
        this.name = null;
        this.email = null;
        this.password = null;
        this.confirmPassword = null;
        //if the passwords do not match, we do not send a call to the backend for security purposes
      } else if (this.password != this.confirmPassword) {
        window.alert("Passwords do not match!");
        this.password = null;
        this.confirmPassword - null;
      } else {
        AXIOS.post(
          "/student/create/",
          {
            name: name,
            email: email,
            password: password
          },
          {}
        )
          .then(response => {
            this.email = null;
            this.name = null;
            this.password = null;
            this.confirmPassword = null;
            window.alert("Sign-up Successful! Please sign in.")
            window.location.href = "/";
          })
          .catch(e => {
            window.alert(e);
            this.email = null;
            this.name = null;
            this.password = null;
            this.confirmPassword = null;
          });
      }
    }
  }
};
</script>

<style></style>
