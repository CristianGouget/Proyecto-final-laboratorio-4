<template>
  <main>
    <Navbar />
    <div class="my-5">
      <div class="mx-auto w-50">
        <h2 class="text-center mb-3">Detalles del Préstamo</h2>
        <div class="row">
          <div class="col-md-12 mb-3">
            <label class="form-label" style="font-weight: bold;">ID del Préstamo:&nbsp;</label>
            <span>{{ loan.idLoan }}</span>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 mb-3">
            <label class="form-label" style="font-weight: bold;">Fecha del Préstamo:&nbsp;</label>
            <span>{{ loan.loanDate }}</span>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 mb-3">
            <label class="form-label" style="font-weight: bold;">Estado del Préstamo:&nbsp;</label>
            <span>{{ loan.loanStatus }}</span>
          </div>
        </div>

        <!-- Display book information -->
        <div class="row">
          <div class="col-md-12 mb-3">
            <h3 class="text-center mb-2">Información del Libro</h3>
            <label class="form-label" style="font-weight: bold;">Título:&nbsp;</label>
            <span>{{ book.title }}</span>
          </div>
          <div class="col-md-12 mb-3">
            <label class="form-label" style="font-weight: bold;">Autor:&nbsp;</label>
            <span>{{ book.author }}</span>
          </div>
          <!-- Add other book details as needed -->
        </div>

        <!-- Buttons for actions -->
        <div class="row d-flex justify-content-center align-items-center">
          <div class="col-md-3 form-group">
            <button class="btn btn-primary mt-3" @click="returnLoan">Marcar como Devuelto</button>
          </div>
          <div class="col-md-3 form-group text-center">
            <router-link to="/loans">
              <button class="btn btn-danger mt-3">Cancelar</button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../components/Navbar.vue';

export default {
  name: 'LoanDetails',
  components: {
    Navbar
  },

  data() {
    return {
      loan: {
        idLoan: '',
        loanDate: '',
        loanStatus: 'Devuelto',
        book: {
          id: '',
          title: '',
          author: '',
          genre: '',
          pageNro: '',
          synopsis: '',
          status: 'Disponible'
        }
        // Add other loan properties...
      },
      book: {
        id: '',
        title: '',
        author: '',
        genre: '',
        pageNro: '',
        synopsis: '',
        status: 'Disponible'
      }
    };
  },

  beforeMount() {
    this.getLoanDetails();
  },

  methods: {
    async getLoanDetails() {
      try {
        const loanResponse = await fetch(`http://localhost:8080/loan/${this.$route.params.idLoan}`);
        this.loan = await loanResponse.json();

        const bookResponse = await fetch(`http://localhost:8080/book/${this.loan.book.id}`);
        this.book = await bookResponse.json();

        console.log(this.loan);
        console.log(this.book);
      } catch (error) {
        console.error('Error in getLoanDetails:', error);
        // Handle the error, such as showing an alert to the user
      }
    },

    async updateBook() {
      try {
        const response = await fetch(`http://localhost:8080/book`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.book)
        });

        console.log(response);
        //this.$router.push('/');
      } catch (error) {
        console.error('Error in updateBook:', error);
        // Handle the error, such as showing an alert to the user
      }
    },

    async updateLoan() {
      try {
        const response = await fetch(`http://localhost:8080/loan`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.loan)
        });

        console.log(response);
        //this.$router.push('/loans');
      } catch (error) {
        console.error('Error in updateLoan:', error);
        // Handle the error, such as showing an alert to the user
      }
    },

    async returnLoan() {
      try {
        // Update the status of the corresponding book to Disponible
        this.book.status = 'Disponible';
        this.loan.loanStatus = 'Devuelto'; //Aca tenía el error, decía status en lugar del atributo correcto loanStatus

        // Perform the updateBook action
        await this.updateBook();

        // Perform the updateLoan action
        await this.updateLoan();

        // Redirect back to the main page after completing the actions
        this.$router.push('/loans');
      } catch (error) {
        console.error('Error in returnLoan:', error);
        // Handle the error, such as showing an alert to the user
      }
    },
  }
}
</script>
