import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 3000, // 前端开发端口
    host: "0.0.0.0"
  }
});
