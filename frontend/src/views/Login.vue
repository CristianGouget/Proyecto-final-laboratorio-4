<template>
  <main>
    <Navbar />

    <!-- Login Form -->
    <div class="container mt-4">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <h1 class="text-center">Login</h1>
          <form @submit.prevent="login">
            <div class="mb-3">
              <label for="username" class="form-label">Username:</label>
              <input type="text" v-model="username" class="form-control" id="username" required />
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">Password:</label>
              <input type="password" v-model="password" class="form-control" id="password" required />
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
          </form>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
//import Navbar from '../components/Navbar.vue';

export default {
  name: 'Login',
  components: {
    //Navbar,
  },
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    login() {
      // Send a request to the server to authenticate the user
      fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: this.username,
          password: this.password,
        }),
      })
        .then(response => {
          if (response.ok) {
            // Authentication successful, redirect to the main page or do something else
            this.$router.push('/books');
          } else {
            // Authentication failed, show an error message or handle accordingly
            console.error('Login failed');
          }
        })
        .catch(error => {
          console.error('Error during login:', error);
        });
    },
  },
};
</script>

<style scoped>
/* Add any custom styles or media queries here */
</style>
