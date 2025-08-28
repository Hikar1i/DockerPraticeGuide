FROM alpine

RUN mkdir /add_dest && mkdir /copy_dest

ADD resource/宿主机tar归档文件.tar /add_dest
#ADD resource/宿主机gz压缩包.tar.gz /add_dest
#ADD resource/宿主机xz压缩包.tar.xz /add_dest

COPY resource/宿主机tar归档文件.tar /copy_dest

ADD "http://10.10.64.98/chenweiguang/docker-guide/raw/master/README.md" /add_dest