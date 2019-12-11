<template>
  <div>
      <div class="wrapper-register col-md-12 d-flex justify-content-center">
        <div class="col-md-4">
          <form @submit.prevent="setBook">
            <div class="form-group">
              <label for="titulo">Novo Titulo</label>
              <input type="email" class="form-control" v-model="book.name" placeholder="Titulo">
            </div>
            <div class="form-group">
              <label for="editora">Editora</label>
              <input type="email" class="form-control" v-model="book.publisher" placeholder="Editora">
            </div>
            <div class="form-group">
              <label for="categoria">Categoria</label>
              <input type="email" class="form-control" v-model="book.category" placeholder="Categoria">
            </div>
            <div class="d-flex justify-content-between">
              <div class="form-group">
                <label for="quantidade">Quantidade</label>
                <input type="email" class="form-control" v-model="book.stock" placeholder="Qtd">
              </div>
              <div class="form-group">
                <label for="price">Preço</label>
                <input type="email" class="form-control" v-model="book.category" placeholder="Preço">
              </div>
            </div>
            <button type="submit" class="btn btn-dark">Cadastrar</button>
          </form>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  data: function(){
    return{
      book:{
        name: '',
        publisher: '',
        category: '',
        stock: '',
        price: ''
      }
    }
  },
  methods:{
    setBook(){
        this.$http.post('/books/book', {
            name: this.book.name,
            publisher: this.book.publisher,
            category: this.book.category,
            stock: this.book.stock,
            price: this.book.price
        }).then(response => {
            this.response = response
            alert('Cadastrado com sucesso');
        }).catch(error => {
            this.response = 'Error: ' + error.response.status
        })
    }

  },
  mounted() {
    
  },
  created() {
    
  },
}
</script>

<style lang="scss" scoped>

</style>