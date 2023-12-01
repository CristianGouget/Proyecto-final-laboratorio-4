<template>
  <main>
    <Navbar />

    <!-- Search Bar and Add Button -->
    <div class="container mb-4">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <input
            type="text"
            class="form-control"
            placeholder="Filtrar por título, autor o género"
            v-model="searchQuery"
          />
        </div>
        <div class="col-md-6">
          <a href="/addbook" class="btn btn-primary">Agregar libro</a>
        </div>
      </div>
    </div>

    <!-- Table-->
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-center">Libros</h1>

          <!-- Table Responsive Wrapper -->
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col" @click="sortBooks('id')">Id</th>
                  <th scope="col">Título</th>
                  <th scope="col">Autor</th>
                  <th scope="col">Género</th>
                  <th scope="col">Nro. Páginas</th>
                  <th scope="col">Sinopsis</th>
                  <th scope="col">Estado</th>
                  <th scope="col">Acciones</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="book in filteredBooks" :key="book.id">
                  <th scope="row">{{ book.id }}</th>
                  <td>{{ book.title }}</td>
                  <td>{{ book.author }}</td>
                  <td>{{ book.genre }}</td>
                  <td>{{ book.pageNro }}</td>
                  <td>{{ book.synopsis }}</td>
                  <td>{{ book.status }}</td>
                  <td>
                    <a class="btn btn-primary" :href="`/editbook/${book.id}`">Editar</a>
                  </td>
                  <td>
                    <button class="btn btn-danger mx-2" @click="confirmDelete(book.id)">Borrar</button>
                  </td>
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
  name: 'ViewBooks',
  components: {
    Navbar,
  },
  data() {
    return {
      books: [],
      searchQuery: '',
      sortKey: 'id',
      sortOrders: {
        'id': 1,
      },
    };
  },

  computed: {
    filteredBooks() {
      const query = this.searchQuery.toLowerCase();
      const filtered = this.books.filter((book) =>
        book.title.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        book.author.toLowerCase().includes(query) ||
        book.genre.toLowerCase().includes(query)
      );

      return filtered.sort((a, b) => {
        const order = this.sortOrders[this.sortKey];
        return order * (a[this.sortKey] - b[this.sortKey]);
      });
    },
  },

  beforeMount() {
    this.getBooks();
  },

  methods: {
    getBooks() {
      fetch('http://localhost:8080/books')
        .then(res => res.json())
        .then(data => {
          this.books = data;
          console.log(data);
        });
    },
    confirmDelete(id) {
      const book = this.books.find(book => book.id === id);
      if (window.confirm(`¿Está seguro de que desea borrar el libro con ID ${id} y título "${book.title}"?`)) {
        this.deleteBook(id);
      }
    },
    deleteBook(id) {
      fetch(`http://localhost:8080/book/${id}`, {
        method: 'DELETE',
      })
        .then(data => {
          console.log(data);
          this.getBooks();
        });
    },
    sortBooks(key) {
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
