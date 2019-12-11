<template>
    <div class="wrapper-login col-md-12 d-flex justify-content-center">
        <div class="col-md-4">
            <form @submit.prevent="setUser">
                <div class="form-group">
                <label for="userEmail">Endereço de email</label>
                <input type="email" class="form-control" id="userEmail" v-model="userInfos.email" aria-describedby="emailHelp" placeholder="Ex. joaodasilva@gmail.com">
                <small id="emailHelp" class="form-text text-muted">Nunca vamos compartilhar seu email, com ninguém.</small>
                </div>
                <div class="form-group">
                <label for="userPass">Senha</label>
                <input type="password" class="form-control" id="userPass" v-model="userInfos.senha" placeholder="Senha">
                </div>
                <button type="submit" class="btn btn-primary">Entrar</button>
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
        email: '',
        senha: ''
      }
    }
  },
  methods:{

  },
  mounted() {
    
  },
  created() {
    
  }

}
</script>

<style lang="scss" scoped>
.wrapper-login{
    background: linear-gradient(45deg, rgba(155,184,254,1) 0%, rgba(222,240,230,1) 100%);
    padding-top: 10%;
    min-height: 100vh;
  }
</style>