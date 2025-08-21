<template>
  <div id="app">
    <h1>📚 图书管理系统 (Vue 3)</h1>
    <div style="margin-bottom: 10px;">
      <input v-model="newBook" placeholder="输入书名" />
      <input v-model="author" placeholder="输入用户名" />
      <button @click="addBook">添加</button>
    </div>
    <ul>
      <li v-for="book in books" :key="book.id">
        {{ book.title }} by {{ book.author || "Unknown" }}
        <button @click="deleteBook(book.id)">删除</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

// 从环境变量读取 API 基础地址，默认 http://localhost:8080
const API_BASE = import.meta.env.VITE_API_BASE || "http://localhost:8080";
const API = `${API_BASE}/api/books`;

const books = ref([]);
const newBook = ref("");
const author = ref(""); // 新增用户名输入框绑定

// 加载书籍
async function loadBooks() {
  const res = await axios.get(API);
  books.value = res.data;
}

// 添加书籍
async function addBook() {
  if (!newBook.value) return;
  await axios.post(API, {
    title: newBook.value,
    author: author.value || "Anonymous" // 如果用户没输入，仍默认 Anonymous
  });
  newBook.value = "";
  author.value = ""; // 清空用户名输入框
  loadBooks();
}

// 删除书籍
async function deleteBook(id) {
  await axios.delete(`${API}/${id}`);
  loadBooks();
}

onMounted(loadBooks);
</script>
