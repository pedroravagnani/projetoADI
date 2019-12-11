<template>
  <div class="col-md-12 justify-content-center p-3">
    <div class="col-md-12 justify-content-center p-3">
      <select class="select" name="" id="" v-model="selected" @change="filterBooks('author', selected)">
        <option :selected="true">Selecione</option>
        <option v-bind:value="book.author" v-for="book in books" :key="book.id">{{ book.author }}</option>
     </select>
    </div>
    
    <table class="table table-bordered col-md-12">
      <thead class="thead-dark ">
        <tr>
          <th class="header-col" scope="col" v-on:click="sortBooks('name', 'asc')">Livro</th>
          <th class="header-col" scope="col" v-on:click="sortBooks('author', 'asc')">Autor</th>
          <th class="header-col" scope="col" v-on:click="sortBooks('editora', 'asc')">Editora</th>
          <th class="header-col" scope="col" v-on:click="sortBooks('price', 'asc')">Preço</th>
          <th class="header-col" scope="col" v-on:click="sortBooks('qtd', 'asc')">Qtd</th>
          <th class="header-col" scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in filtered" :key="book.id">
          <th scope="row">{{ book.name }}</th>
          <td>{{ book.author }}</td>
          <td>{{ book.editora }}</td>
          <td>{{ book.price }}</td>
          <td>{{ book.qtd }}</td>
          <td><button class="btn btn-success"> Comprar </button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
var sorted = false;
export default {
  data: function(){
    return{
      books:[
        {
          id:'1',name: 'O melhor de mim',author:'Nicholas Sparks',editora:'Editora Arqueiro' ,price: 29.99, qtd: 10
        },
        {
          id:'2',name: 'Texto crueis demais para serem lidos rapidamente',author:'Igor Pires, Gabriela Barreira',editora:'Globo Livros' ,price: 19.99, qtd: 8
        },
        {
          id:'3',name: 'Só A Gente Sabe O Que Sente',author:'Frederico Elboni',editora:'Editora Saraiva' ,price: 25.99, qtd: 10
        },
        {
          id:'4',name: 'Um Dia: Vinte anos, duas pessoas',author:'David Nicholls',editora:'intrínseca', price: 30.00, qtd: 5
        }
      ],
      selected: "Selecione",
      filtered: []
    }
  },
  methods:{
    getBooks: function(){
      this.$http.get('/books/all').then(response => {
        console.log(response);
        this.books = response.data;
      }).catch(error => {
        this.response = 'Error: ' + error.response;
      })
    },

    sortBooks: function( value, order ) {
      if( sorted ){
        this.filtered.sort(this.compareValues(value, 'desc'));
        sorted = false;
      }else {
        this.filtered.sort(this.compareValues(value, order));
        sorted = true;
      }
    },
    filterBooks: function ( key, value ) {
      if( value == 'Selecione' ){
        this.filtered = this.books;
        return;
      }
      
      let filtered = this.books.filter(function (item) {
        return item[key] == value;
      })
      this.filtered = filtered;
    },

    compareValues: function(key, order = 'asc') {
      return function innerSort(a, b) {
        if (!a.hasOwnProperty(key) || !b.hasOwnProperty(key)) {
          // property doesn't exist on either object
          return 0;
        }

        const varA = (typeof a[key] === 'string')
          ? a[key].toUpperCase() : a[key];
        const varB = (typeof b[key] === 'string')
          ? b[key].toUpperCase() : b[key];

        let comparison = 0;
        if (varA > varB) {
          comparison = 1;
        } else if (varA < varB) {
          comparison = -1;
        }
        return (
          (order === 'desc') ? (comparison * -1) : comparison
        );
      };
    }
  },
  mounted() {
    this.filtered = this.books;
  },
  created() {

  }
}


</script>

<style lang="scss" scoped>
  .header-col { cursor: pointer; }
  .select { background: #fff; border: 1px dashed #ccc; padding: 5px; }
</style>