# app.py - 基础 Flask Web 应用
from flask import Flask, jsonify
import os
import socket
import datetime

app = Flask(__name__)

@app.route('/')
def hello():
    return jsonify({
        'message': 'Hello Docker World!',
        'hostname': socket.gethostname(),
        'timestamp': datetime.datetime.now().isoformat(),
        'environment': dict(os.environ)
    })

@app.route('/health')
def health():
    return jsonify({'status': 'healthy', 'time': datetime.datetime.now().isoformat()})

@app.route('/config')
def config():
    # 演示环境变量配置读取
    db_url = os.getenv('DATABASE_URL', 'not-set')
    debug_mode = os.getenv('DEBUG', 'false').lower() == 'true'

    return jsonify({
        'database_url': db_url,
        'debug_mode': debug_mode,
        'host': socket.gethostname()
    })

if __name__ == '__main__':
    # 从环境变量获取端口，默认 5000
    port = int(os.getenv('PORT', 5000))
    debug = os.getenv('DEBUG', 'false').lower() == 'true'

    print(f"Starting server on port {port}, debug: {debug}")
    app.run(host='0.0.0.0', port=port, debug=debug)