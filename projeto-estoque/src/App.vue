<template>
  <div id="app">

    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Estoque de Produtos</a>
      </div>
    </nav>

    <div class="container">
      <form @submit.prevent="salvar">
          
          <label>Nome</label>
          <input type="text" placeholder="Nome" v-model="produto.nome" required> 
          
          <div class="row">
            <div class="col s2">
              <label>Quantidade</label>
              <input type="number" placeholder="Quantidade" v-model="produto.quantidade" required>
            </div>
            <div class="col s2">
              <label>Valor</label>
              <input type="number" placeholder="Valor" v-model="produto.preco" required>
            </div>
          </div>
          
          <label>Descrição</label>
          <input type="text" placeholder="Descrição" v-model="produto.descricao">
          
          <label>Unidade</label>
        <form action="#">
          <p>
            <label>
              <input name="group1" type="radio" v-model="produto.unidade" v-bind:value="'KG'" />
              <span>Kg</span>
            </label>
          </p>
          <p>
            <label>
              <input name="group1" type="radio" v-model="produto.unidade" v-bind:value="'LT'"/>
              <span>Litro</span>
            </label>
          </p>
          <p>
            <label>
              <input name="group1" type="radio" v-model="produto.unidade" v-bind:value="'UN'" checked/>
              <span>Unidade</span>
            </label>
          </p>
        </form>
            
          
          <form action="#">
            <p>
              <label>
                <input type="checkbox" v-model="produto.ativo"/>
                <span>Ativo</span>
              </label>
            </p>
          </form>

          <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>

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
            <td>{{ produto.preco }}</td>
            <td>{{ produto.unidade }}</td>
            <td>{{ new Date (produto.dataCadastro).toLocaleDateString() }}</td>
            <td>{{ produto.descricao }}</td>
            <td>{{ produto.ativo }}</td>
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
      produtos:[]
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
