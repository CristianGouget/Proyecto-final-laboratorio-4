<template>
    <main>
      <Navbar />
  
      <!-- Search Bar -->
      <div class="container mb-4">
        <div class="row justify-content-center">
          <div class="col-md-6">
            <input
              type="text"
              class="form-control"
              placeholder="Filtrar por título o estado"
              v-model="searchQuery"
            />
          </div>
        </div>
      </div>
  
      <!-- Table-->
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-center">Préstamos</h1>
            <!-- Add button -->
            <a href="/addloan" class="btn btn-primary">Registrar préstamo</a>
            <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col">Id</th>
                  <th scope="col">Fecha del préstamo</th>
                  <th scope="col">Estado del préstamo</th>
                  <th scope="col">Título</th>
                  <th scope="col">Autor</th>
                  <th scope="col">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="loan in filteredLoans" :key="loan.idLoan">
                  <th scope="row">{{ loan.idLoan }}</th>
                  <td>{{ loan.loanDate }}</td>
                  <td>{{ loan.loanStatus }}</td>
                  <td>{{ loan.book.title }}</td>
                  <td>{{ loan.book.author }}</td>
                  <td>
                    <a class="btn btn-primary" :href="`/editloan/${loan.idLoan}`">Marcar devuelto</a>
                    <button class="btn btn-danger mx-2" @click="confirmDelete(loan.idLoan)">Borrar</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </main>
  </template>
  
  <script>
  import Navbar from '../components/Navbar.vue';
  
  export default {
    name: 'ViewLoans',
    components: {
      Navbar,
    },
    data() {
      return {
        loans: [],
        searchQuery: '',
      };
    },
  
    computed: {
      filteredLoans() {
        const query = this.searchQuery.toLowerCase();
        return this.loans.filter((loan) =>
          loan.loanStatus.toLowerCase().includes(query) ||
          loan.book.title.toLowerCase().includes(query)
        );
      },
    },
  
    beforeMount() {
      this.getLoans();
    },
  
    methods: {
      getLoans() {
        fetch('http://localhost:8080/loans')
          .then((res) => res.json())
          .then((data) => {
            this.loans = data;
            console.log(data);
          });
      },
      confirmDelete(idLoan) {
        const loan = this.loans.find((loan) => loan.idLoan === idLoan);
        if (
          window.confirm(
            `¿Está seguro de que desea borrar el préstamo con ID ${idLoan} y título "${loan.book.title}"?`
          )
        ) {
          this.deleteLoan(idLoan);
        }
      },
      deleteLoan(idLoan) {
        fetch(`http://localhost:8080/loan/${idLoan}`, {
          method: 'DELETE',
        })
          .then((response) => response.json())
          .then((data) => {
            console.log(data);
            this.getLoans();
            // Refresh the page by reloading the current route
            this.$forceUpdate();
          });
      },
    },
  };
  </script>
  