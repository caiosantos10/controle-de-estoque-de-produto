<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Estoque de Produtos</a>
      </div>
    </nav>
    
    <div>
      <h5 class="center-align">Cadastre seu produto</h5>
    </div>  
    
    <div class="container">
      <form @submit.prevent="salvar">

          <div class="row">
            <div class="col s7">
              <label>Nome *</label>
              <input type="text" placeholder="Nome" v-model="produto.nome" required> 
            </div>
            <div class="col s1">
              <label>Valor *</label>
              <input id="preco" placeholder="Valor" step="0.010" v-model="produto.preco" required>
            </div>
            <div class="col s2">
              <label>Quantidade *</label>
              <input type="number" placeholder="Quantidade" v-model="produto.quantidade" required>
            </div>
            <div class="col s2">
              <label>Está Ativo?</label>
              <div class="switch">
                <label>
                  Off
                  <input type="checkbox" v-model="produto.ativo"/>
                  <span class="lever"></span>
                  On
                </label>
              </div>
              </div>
          </div>

          <div class="row">
            <div class="col s10">
              <label>Descrição</label>
              <input type="text" placeholder="Descrição" v-model="produto.descricao">
            </div>
            <div class="col s1">
              <label>Unidade</label>
              <form action="#">
                
                  <label>
                    <input name="group1" type="radio" v-model="produto.unidade" v-bind:value="'KG'" />
                    <span>Kg</span>
                  </label>
                
                  <label>
                    <input name="group1" type="radio" v-model="produto.unidade" v-bind:value="'LT'"/>
                    <span>Litro</span>
                  </label>
                
                  <label>
                    <input name="group1" type="radio" v-model="produto.unidade" v-bind:value="'UN'" checked/>
                    <span>Unidade</span>
                  </label>
                
              </form>
            </div>
            <button class="waves-effect waves-light btn-small ">Salvar<i class="material-icons left">save</i></button>
          </div>

      </form>

       <div>
        <h5 class="center-align">Lista de produtos</h5>
      </div> 

      <!--Filtragem de produtos por nome--> 
      <form @submit.prevent="buscarPorNome">
        <div class="row ">
          <div>
            <label>Filtrar por:</label>
            <input type="text" placeholder="Nome" v-model="aux.nome"> 
          </div>
             <button class="waves-effect waves-light btn-small">Buscar<i class="material-icons left">search</i></button>
        </div>
      </form>

      <!--Filtragem de produtos por ativo/inativo -->
      <form class="" @submit.prevent="buscarPorAtivo">
        <div class="row ">
          <div class="col s3">
            <label>Filtrar por:</label>
            <label>Está Ativo?</label>
              <div class="switch">
                <label>
                  Off
                  <input type="checkbox" v-model="aux.ativo"/>
                  <span class="lever"></span>
                  On
                </label>
              </div>   
          </div>
             <button class="waves-effect waves-light btn-small">Buscar<i class="material-icons left">search</i></button>
        </div>
      </form>

      <!--Filtragem de produtos por Data -->
      <form class="" @submit.prevent="buscarPorBeforeData">
        <div class="row ">
          <div>
            <label>Filtrar por:</label>
            <input type="date" placeholder="Até a data..." v-model="aux.dataCadastro"> 
          </div>
             <button class="waves-effect waves-light btn-small">Buscar<i class="material-icons left">search</i></button>
        </div>
      </form>
      

      <table>

        <thead>

          <tr>
            <th>NOME</th>
            <th>QTD</th>
            <th>VALOR</th>
            <th>UNIDADE</th>
            <th>DATA CADASTRO</th>
            <th>DESCRIÇÃO</th>
            <th>ATIVO?</th>
            <th>OPÇÕES</th>
          </tr>

        </thead>

        <tbody>

          <tr v-for="produto of produtos" :key="produto.id">
            <td>{{ produto.nome }}</td>
            <td>{{ produto.quantidade }}</td>
            <td>R$ {{ produto.preco }}</td>
            <td>{{ produto.unidade }}</td>
            <td>{{ new Date (produto.dataCadastro).toLocaleDateString() }}</td>
            <td>{{ produto.descricao }}</td>
            <td>{{ true == produto.ativo ? 'SIM' : 'NÃO' }}</td>
            <td>
              <button @click="editar(produto)" class="waves-effect btn-small blue darken-1"><i class="material-icons">create</i></button>
              <button @click="deletar(produto)" class="waves-effect btn-small red darken-1"><i class="material-icons">delete_sweep</i></button>
            </td>

          </tr>

        </tbody>
      
      </table>

    </div>

  </div>
</template>

<script>

import Produto from './services/produtos'

export default {

  data(){
    return {
      produto: {
        id:'',
        nome:'',
        quantidade:'',
        preco:'',
        unidade:'',
        dataCadastro: '',
        descricao:'',
        ativo:''
      },
      produtos:[],
      aux: {
        nome:'',
        dataCadastro:'',
        ativo:''
      }
    }
  },

  mounted(){
    this.listar()   
  },

  methods:{
    listar(){
      Produto.listar().then(resposta => {
        this.produtos = resposta.data
      })  
    },

    buscarPorNome(){
      if(!this.aux.nome){
        return this.listar();
      }
      Produto.buscarPorNome(this.aux.nome).then(resposta => {
        this.produtos = resposta.data
        //console.log(resposta.data)
        this.aux = {}
      })
    },

    buscarPorAtivo(){
      Produto.buscarPorAtivo(this.aux.ativo).then(resposta => {
        this.produtos = resposta.data
        //console.log(resposta.data)
        this.aux = {}
      })
    },

    buscarPorBeforeData(){
      Produto.buscarPorBeforeData(new Date(Date(this.aux.dataCadastro)).getTime()).then(resposta => {
        this.produtos = resposta.data
        console.log(resposta.data)
        this.aux = {}
      })
    },
    
    salvar(){
      
      if(!this.produto.id){
            this.produto.dataCadastro = new Date().getTime();
            Produto.salvar(this.produto).then(resposta => {
            this.produto = {}
            alert('Salvo com sucesso!')
            this.listar()
          }).catch(e => {
            console.log(e.response.data)
          })
      }else{
         Produto.atualizar(this.produto).then(resposta => {
            this.produto = {}
            alert('Atualizado com sucesso!')
            this.listar()
          }).catch(e => {
            console.log(e.response.data)
          })

      }
      
    },

    editar(produto){
      this.produto = produto
    },

    deletar(produto){
      if(confirm('Deseja excluir este produto?')){
          Produto.deletar(produto).then(resposta => {
          this.listar();
        }).catch(e => {

        })

      }
      
    }

  }

}

</script>

<style>
 
</style>
