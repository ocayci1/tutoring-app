import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/components/Welcome'
import Login from '@/components/Login'
import Profile from '@/components/Profile'
import RequestCourse from '@/components/RequestCourse'
import RequestCourseForm from '@/components/RequestCourseForm'
import BookingForm from '@/components/BookingForm'
import ReviewForm from '@/components/ReviewForm'
import TutorProfileDummy from '@/components/TutorProfileDummy'
import Admin from '@/components/Admin'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Welcome',
      component: Welcome
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/profile',
      name: 'Profile',
      component: Profile
    },
    {
      path:'/requestCourse',
      name: 'RequestCourse',
      component: RequestCourse
    },
	{
		path: '/requestCourseForm',
		name: 'RequestCourseForm',
		component: RequestCourseForm
	},
	{
		path: '/bookingForm',
		name: 'BookingForm',
		component: BookingForm
	},
	{
		path: '/reviewForm',
		name: 'ReviewForm',
		component: ReviewForm
	},
	{
		path: '/tutorProfile',
		name: 'TutorProfileDummy',
		component: TutorProfileDummy
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin
  }
  ]
})
