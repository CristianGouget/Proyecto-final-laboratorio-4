<template>
  <main>
    <Navbar />
    <div class="my-5">
      <div class="mx-auto w-25 ">
        <h2 class="text-center mb-3">Editar Libro</h2>
        <form @submit.prevent="updateBook">
          <!--Title-->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="title" class="form-label" style="font-weight: bold;">Título</label>
              <input id="title" type="text" name="title" class="form-control" placeholder="Título" required
                v-model="book.title">
            </div>
          </div>


          <!--Author-->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="author" class="form-label" style="font-weight: bold;">Autor</label>
              <input id="author" type="text" name="author" class="form-control" placeholder="Autor" required
                v-model="book.author">
            </div>
          </div>

          <!--Genre-->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="genre" class="form-label" style="font-weight: bold;">Género</label>
              <input id="genre" type="text" name="genre" class="form-control" placeholder="Género" required
                v-model="book.genre">
            </div>
          </div>

          <!--Number of pages-->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="pageNro" class="form-label" style="font-weight: bold;">Número de páginas</label>
              <input id="pageNro" type="number" name="pageNro" class="form-control" placeholder="Número de páginas"
                required v-model="book.pageNro">
            </div>
          </div>

          <!--Sinopsis-->
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="synopsis" class="form-label" style="font-weight: bold;">Sinopsis</label>
              <input id="synopsis" type="text" name="synopsis" class="form-control" placeholder="Sinopsis" required
                v-model="book.synopsis">
            </div>
          </div>

          <!--Status-->
          <label for="status" class="form-label" style="font-weight: bold;">Estado</label>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="status" id="disponible" value="Disponible"
              v-model="book.status">
            <label class="form-check-label" for="disponible">Disponible</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="status" id="prestado" value="Prestado"
              v-model="book.status">
            <label class="form-check-label" for="prestado">Prestado</label>
          </div>

          <div class="row d-flex justify-content-center align-items-center">  <!--Con esto alineé los botones al centro de la pantalla -->
            <div class="col-md-3 form-group">
              <input class="btn btn-primary mt-3" type="submit" value="Editar libro">
            </div>
            <div class="col-md-3 form-group text-center">
              <!-- Updated Cancel button styling -->
              <router-link to="/">
                <button class="btn btn-danger mt-3">Cancelar</button>
              </router-link>
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
  name: 'UpdateBook',
  components: {
    Navbar
  },

  data() {
    return {
      book: {
        id: '',
        title: '',
        author: '',
        genre: '',
        pageNro: '',
        synopsis: '',
        status: ''
      }
    }
  },

  beforeMount() {
    this.getBooks();
  },

  methods: {
    getBooks() {
      fetch(`http://localhost:8080/book/${this.$route.params.id}`)
        .then(res => res.json())
        .then(data => {
          this.book = data;
          console.log(this.book);
        })

    },
    updateBook() {
      fetch(`http://localhost:8080/book`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.book)
      })
        .then(data => {
          console.log(data);
          this.$router.push('/books');
        })
    }
  }
}

</script>