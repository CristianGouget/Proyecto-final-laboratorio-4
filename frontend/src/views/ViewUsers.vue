<template>
  <main>
    <Navbar />

    <!-- Table-->
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="col-md-6">
          <a href="/adduser" class="btn btn-primary">Agregar Usuario</a>
        </div>
          <h1 class="text-center">Usuarios</h1>

          <!-- Table Responsive Wrapper -->
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col" @click="sortUsers('userId')">Id</th>
                  <th scope="col">Nombre de usuario</th>
                  <th scope="col">Rol</th>
                 <!-- <th scope="col">Acciones</th>-->
                </tr>
              </thead>
              <tbody>
                <tr v-for="user in users" :key="user.userId">
                  <th scope="row">{{ user.userId }}</th>
                  <td>{{ user.username }}</td>
                  <td>{{ user.role }}</td>
                  <!--<td>
                    <a class="btn btn-primary" :href="`/edituser/${user.userId}`">Editar</a>
                  </td>
                  <td>
                    <button class="btn btn-danger mx-2" @click="confirmDelete(user.userId)">Borrar</button>
                  </td>-->
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../components/Navbar.vue';

export default {
  name: 'ViewUsers',
  components: {
    Navbar,
  },
  data() {
    return {
      users: [],
      sortKey: 'userId',
      sortOrders: {
        'userId': 1,
      },
    };
  },

  beforeMount() {
    this.getUsers();
  },

  methods: {
    getUsers() {
      fetch('http://localhost:8080/users') // Replace with your actual endpoint for fetching users
        .then(res => res.json())
        .then(data => {
          this.users = data;
          console.log(data);
        });
    },
    sortUsers(key) {
      this.sortKey = key;
      this.sortOrders[key] = this.sortOrders[key] * -1;
    },
  },
};
</script>

<style scoped>
/* Add any custom styles or media queries here */
@media (min-width: 768px) {
  .table th, .table td {
    font-size: 16px;
  }
}
</style>
