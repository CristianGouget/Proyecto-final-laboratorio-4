<template>
  <main>
    <Navbar />
    <div class="my-5">
      <div class="mx-auto w-25" style="max-width: 100%;">
        <h2 class="text-center mb-3">Agregar Préstamo</h2>
        <form @submit.prevent="addLoan">

          <!-- Dropdown for selecting a book -->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="selectedBook" class="form-label" style="font-weight: bold;">Seleccionar Libro</label>
              <select id="selectedBook" name="selectedBook" class="form-control" v-model="loan.bookId" required>
                <option v-for="book in availableBooks" :key="book.id" :value="book.id">{{ book.title }}</option>
              </select>
            </div>
          </div>

          <!-- Date picker for loanDate -->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="loanDate" class="form-label" style="font-weight: bold;">Fecha del Préstamo</label>
              <input id="loanDate" type="date" name="loanDate" class="form-control" v-model="loan.loanDate" required>
            </div>
          </div>

          <!-- Other loan information fields -->
          <!-- ... -->

          <div class="row">
            <div class="col-md-12 form-group">
              <input class="btn btn-primary w-100" type="submit" value="Registrar préstamo">
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
  name: 'AddLoan',
  components: {
    Navbar
  },
  

  data() {
    return {
      loan: {
        bookId: null,
        loanDate: '', // Use a date format like 'yyyy-mm-dd'
        // Add other loan properties...
        loanStatus: 'Pendiente'
      },
      availableBooks: [],
      selectedBook: {
        id: '',
        title: '',
        author: '',
        genre: '',
        pageNro: '',
        synopsis: '',
        status: 'Prestado'
      }
    };
  },

  mounted() {
    this.fetchAvailableBooks();
  },

  methods: {
    fetchAvailableBooks() {
      return fetch('http://localhost:8080/books')
        .then(response => response.json())
        .then(data => {
          // Filter only books with the "Disponible" attribute
          this.availableBooks = data.filter(book => book.status === 'Disponible');
        })
        .catch(error => console.error('Error fetching available books:', error));
    },

    registerLoan(loanPayload) {
      return fetch('http://localhost:8080/addloan', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(loanPayload)
      })
        .then(response => {
          console.log(response); // Log the entire response
          return response.json();
        })
        .then(data => {
          console.log(data);
          if (!data.success) {
            throw new Error('Error registering loan');
          }

          // Call the new endpoint to update book status to "Prestado"
          this.updateBookStatus(this.selectedBook.id);
        })
        .catch(error => console.error('Error adding loan:', error));
    },

    getBook() {
      fetch(`http://localhost:8080/book/${this.selectedBook.id}`)
        .then(res => res.json())
        .then(data => {
          this.selectedBook = data;
          console.log(this.selectedBook);
        });
    },

    updateBook() {
      fetch(`http://localhost:8080/book`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.selectedBook)
      })
        .then(data => {
          console.log(data);
        });
    },

    addLoan() {
      // Fetch available books
      this.fetchAvailableBooks()
        .then(() => {
          // Find the selected book from availableBooks based on bookId
          const selectedBook = this.availableBooks.find(book => book.id === this.loan.bookId);

          // Check if the selected book is found
          if (selectedBook) {
            // Assign the selected book to the data property
            this.selectedBook = {
              id: selectedBook.id,
              title: selectedBook.title,
              author: selectedBook.author,
              genre: selectedBook.genre,
              pageNro: selectedBook.pageNro,
              synopsis: selectedBook.synopsis,
              status: 'Prestado'
            };

            // Create a new object to include both loan and book information
            const loanPayload = {
              book: {
                id: selectedBook.id,
                // Add other book properties if needed
              },
              loanDate: this.loan.loanDate,
              loanStatus: this.loan.loanStatus
              // Add other loan properties
            };

            // Register the loan without updating the book status
            return this.registerLoan(loanPayload);
          } else {
            console.error('Selected book not found');
          }
        })
        .then(() => {
          // After registering the loan, get and update books
          this.getBook();
          this.updateBook();
        })
        .then(() => {
          this.$router.push("/loans");
        })
        .catch(error => console.error('Error adding loan:', error));
    }
  }
};
</script>
