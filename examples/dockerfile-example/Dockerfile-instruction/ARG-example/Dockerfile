FROM alpine

ARG ENTRACE_DIR
ARG TEST_ARG_1="这是一个测试用的ARG参数"

RUN echo "构建过程: ARG参数TEST_ARG_1的值为: ${TEST_ARG_1}" > ${ENTRACE_DIR}/arg.txt

WORKDIR ${ENTRACE_DIR:-/defaultDir}