<template>
  <main>
    <Navbar />
    <div class="my-5">
      <div class="mx-auto w-25 " style="max-width:100%;">
        <h2 class="text-center mb-3">Agregar Libro</h2>
        <form @submit.prevent="addBook">
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
              <input id="pageNro" type="number" name="pageNro" class="form-control" placeholder="Número de páginas" required
                v-model="book.pageNro">
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

          <!--Ejemplo radio button
          <label for="gender" class="form-label">Género</label>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="gender" id="hombre" value="Hombre"
              v-model="patient.gender">
            <label class="form-check-label" for="male">Hombre</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="gender" id="mujer" value="Mujer" v-model="patient.gender">
            <label class="form-check-label" for="female">Mujer</label>
          </div>-->
          <!--<div class="form-check">
                  <input class="form-check-input" type="radio"   name="gender" id="others" checked value="others" v-model="patient.gender">
                  <label class="form-check-label" for="others">Others</label>
                </div>-->

          <div class="row">
            <div class="col-md-12 form-group">
              <input class="btn btn-primary w-100" type="submit" value="Registrar libro">
            </div>
          </div>

          <div>

          </div>
        </form>

      </div>
    </div>
  </main>
</template>


<script>
import Navbar from '../components/Navbar.vue';

export default {
  name: 'AddBook',
  components: {
    Navbar
  },

  data() {
    return {
      book: {
        title: '',
        author: '',
        genre: '',
        pageNro: '',
        synopsis: '',
        status: 'Disponible'
      }
    }
  },

  methods: {
    addBook() {
      fetch('http://localhost:8080/addbook', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.book)
      })
        .then(data => {
          console.log(data)
          this.$router.push("/books");
          //window.location.reload();
          
        })

    }
  },

}


</script>