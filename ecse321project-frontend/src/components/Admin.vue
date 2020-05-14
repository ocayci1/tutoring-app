<template>
  <div id="admin">
    <!-- this Vue.js component is created solely for the testing of create booking, search and reviews. Because the initial plan was to get together
    with other views to work on the project, we still created Tutor and Course objects in our database, which prevented us from testing booking a
    tutoring session, searching tutors and courses, and writing reviews. This page helped us to create tutors and courses in order to overcome this
    hardship -->
    <b-container>
    <h1>Create Tutor</h1>
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
    <b-button
      type="submit"
      variant="outline-success"
      v-on:click="tutor(name, email, password)"
      >Create Tutor</b-button
    >
    <br>
    <br>
    <h1>Create Course</h1>
    <label label-for="courseName">Course Name:</label>
    <br />
    <b-input
      type="text"
      id="courseName"
      name="courseName"
      v-model="courseName"
      placeholder="Introduction to Software Engineering"
    ></b-input>
    <br />
    <label label-for="courseCode">Course Code:</label>
    <br />
    <b-input
      type="text"
      id="courseCode"
      name="courseCode"
      v-model="courseCode"
      placeholder="ECSE 321"
    ></b-input>
    <br />
    <label label-for="school">School:</label>
    <br />
    <b-input
      type="text"
      id="school"
      name="school"
      v-model="school"
      placeholder="McGill University"
    ></b-input>
    <br />
        <label label-for="faculty">Faculty:</label>
    <br />
    <b-input
      type="text"
      id="faculty"
      name="faculty"
      v-model="faculty"
      placeholder="Software Engineering"
    ></b-input>
    <br />
        <label label-for="level">Level:</label>
    <br />
    <b-input
      type="text"
      id="level"
      name="level"
      v-model="level"
      placeholder="Undergraduate"
    ></b-input>
    <br />
    <b-button
      type="submit"
      variant="outline-success"
      v-on:click="course(courseName, courseCode, school, faculty, level)"
      >Create Course</b-button
    >
    </b-container>
  </div>
</template>

<script>
import axios from 'axios'
var config = require("../../config")

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
})

export default {

  name: "admin",

  data () {
    return {
      name: null,
      email: null,
      password: null,
      courseName: null,
      courseCode: null,
      school: null,
      faculty: null,
      level: null,
    }
  },
  methods: {
    //create tutor method to test if we can create and get tutor objects from database
    tutor: function(name, email, password) {
      //create json parameters to be send to the backend
      const params = new URLSearchParams();
      params.append("name", name);
      params.append("email", email);
      params.append("password", password);
      if (
        name == null ||
        email == null ||
        password == null
      ) {
        window.alert("Cannot sign up because of empty fields!");
        this.name = null;
        this.email = null;
        this.password = null;
      } else {
        //post method to activate the createTutor method inside the REST Controller
        AXIOS.post(
          "/tutor/create",
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
          })
          .catch(e => {
            window.alert(e);
            this.email = null;
            this.name = null;
            this.password = null;
          });
      }
    },
    course: function(courseName, courseCode, school, faculty, level) {
      const params = new URLSearchParams();
      params.append("courseName", courseName);
      params.append("courseCode", courseCode);
      params.append("school", school);
      params.append("faculty", faculty);
      params.append("level", level);
      if (
        courseName == null ||
        courseCode == null ||
        school == null ||
        faculty == null ||
        level == null
      ) {
        window.alert("Cannot sign up because of empty fields!");
        this.courseName = null;
        this.courseCode = null;
        this.school = null;
        this.faculty = null;
        this.level = null;
      } else {
        AXIOS.post(
          "/course/create/",
          {
            courseName: courseName,
            courseCode: courseCode,
            school: school,
            faculty: faculty,
            level: level
          },
          {}
        )
          .then(response => {
            this.courseName = null;
            this.courseCode = null;
            this.school = null;
            this.faculty = null;
            this.level = null;
          })
          .catch(e => {
            window.alert(e);
            this.courseName = null;
            this.courseCode = null;
            this.school = null;
            this.faculty = null;
            this.level = null;
          });
      }
    },
  }
}
</script>
