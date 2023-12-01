<template>
  <main>
    <Navbar />
    <div class="my-5">
      <div class="mx-auto w-25 " style="max-width:100%;">
        <h2 class="text-center mb-3">Agregar Usuario</h2>
        <form @submit.prevent="registerUser">
          <!-- Username -->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="username" class="form-label" style="font-weight: bold;">Nombre de Usuario</label>
              <input id="username" type="text" name="username" class="form-control" placeholder="Nombre de Usuario" required
                v-model="user.username">
            </div>
          </div>

          <!-- Password -->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="password" class="form-label" style="font-weight: bold;">Contraseña</label>
              <input id="password" type="password" name="password" class="form-control" placeholder="Contraseña" required
                v-model="user.password">
            </div>
          </div>

          <div class="row">
            <div class="col-md-12 form-group">
              <input class="btn btn-primary w-100" type="submit" value="Registrar Usuario">
            </div>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../components/Navbar.vue';

export default {
  name: 'AddUser',
  components: {
    Navbar
  },

  data() {
    return {
      user: {
        username: '',
        password: ''
      }
    }
  },

  methods: {
    registerUser() {
      fetch('http://localhost:8080/adduser', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.user)
      })
        //.then(response => response.json())
        .then(data => {
          console.log(data);
          // Redirect or handle success as needed
          this.$router.push('/users'); // navigate back to the main page
          //window.location.reload(); // reload the main page
          //this.$router.go(0); // force reload the current route
        })
        .catch(error => {
          console.error('Error registering user:', error);
        });
    }
  },
}
</script>
