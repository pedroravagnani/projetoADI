<template>
  <div class="wrapper-register col-md-12 d-flex justify-content-center">
    <div class="col-md-4">
      <form @submit.prevent="setUser">
        <div class="form-group">
          <label for="username">Nome</label>
          <input type="email" class="form-control" id="username" v-model="userInfos.name" aria-describedby="emailHelp" placeholder="Nome">
        </div>
        <div class="form-group">
          <label for="userEmail">Endereço de email</label>
          <input type="email" class="form-control" id="userEmail" v-model="userInfos.email" aria-describedby="emailHelp" placeholder="Ex. joaodasilva@gmail.com">
          <small id="emailHelp" class="form-text text-muted">Nunca vamos compartilhar seu email, com ninguém.</small>
        </div>
        <div class="form-group">
          <label for="userPass">Senha</label>
          <input type="password" class="form-control" id="userPass" v-model="userInfos.senha" placeholder="Senha">
        </div>
        <button type="submit" class="btn btn-primary">Registrar</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data: function(){
    return{
      userInfos:{
        name : '',
        username : '',
        email: '',
        password: ''
      }
    }
  },
  methods:{
    setUser(){
        this.$http.post('/users/user', {
            name: this.userInfos.name,
            username: this.userInfos.username,
            email: this.userInfos.email,
            password: this.userInfos.senha
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

<style lang="css" scoped>
  .wrapper-register{
    background: linear-gradient(45deg, rgba(222,240,230,1) 0%, rgba(155,184,254,1) 100%);
    padding-top: 10%;
    min-height: 100vh;
  }
</style>