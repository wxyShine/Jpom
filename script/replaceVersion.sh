#!/bin/bash
#
# The MIT License (MIT)
#
# Copyright (c) 2019 Code Technology Studio
#
# Permission is hereby granted, free of charge, to any person obtaining a copy of
# this software and associated documentation files (the "Software"), to deal in
# the Software without restriction, including without limitation the rights to
# use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
# the Software, and to permit persons to whom the Software is furnished to do so,
# subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
# FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
# COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
# IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
# CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#

#-----------------------------------------------------------
# 此脚本用于每次升级时替换相应位置的版本号
#-----------------------------------------------------------

set -o errexit


current_path=$(pwd)
case "$(uname)" in
    Linux)
		bin_abs_path=$(readlink -f $(dirname "$0"))
		;;
	*)
		bin_abs_path=`cd $(dirname $0) || exit; pwd`
		;;
esac

pwd=${bin_abs_path}/../

echo "当前路径：${current_path},脚本路径：${bin_abs_path}"

if [ -n "$1" ];then
    new_version="$1"
    old_version=`cat ${pwd}/docs/.vuepress/public/docs/versions.tag`
    echo "$old_version 替换为新版本 $new_version"
else
    # 参数错误，退出
    echo "ERROR: 请指定新版本！"
    exit
fi

if [ ! -n "$old_version" ]; then
    echo "ERROR: 旧版本不存在，请确认 /docs/.vuepress/public/docs/versions.tag 中信息正确"
    exit
fi


# 替换远程更新包的版本号
sed -i.bak "s/${old_version}/${new_version}/g" ${pwd}/docs/.vuepress/public/docs/versions.json
sed -i.bak "s/${old_version}/${new_version}/g" ${pwd}/docs/.vuepress/public/docs/release-versions.json
sed -i.bak "s/${old_version}/${new_version}/g" ${pwd}/package.json

function updateDocUrlItem(){

mdPath="${pwd}/docs/更新日志/02.下载链接/01.下载链接.md"

if [[ `cat ${mdPath} |grep "$1"` != ""  ]]; then
	echo "下载地址已经更新啦"
else
	echo "" > ${pwd}/temp-docs.log
	echo "## $1" >> ${pwd}/temp-docs.log
	echo "- [jpom-$1.zip](https://download.jpom.top/release/$1/jpom-$1.zip)" >> ${pwd}/temp-docs.log
	echo "- [server-$1-release.tar.gz](https://download.jpom.top/release/$1/server-$1-release.tar.gz) | [sha1sum](https://download.jpom.top/release/$1/server-$1-release.tar.gz.sha1)" >> ${pwd}/temp-docs.log
	echo "- [server-$1-release.zip](https://download.jpom.top/release/$1/server-$1-release.zip) | [sha1sum](https://download.jpom.top/release/$1/server-$1-release.zip.sha1)" >> ${pwd}/temp-docs.log
	echo "- [agent-$1-release.tar.gz](https://download.jpom.top/release/$1/agent-$1-release.tar.gz) | [sha1sum](https://download.jpom.top/release/$1/agent-$1-release.tar.gz.sha1)" >> ${pwd}/temp-docs.log
	echo "- [agent-$1-release.zip](https://download.jpom.top/release/$1/agent-$1-release.zip) | [sha1sum](https://download.jpom.top/release/$1/agent-$1-release.zip.sha1)" >> ${pwd}/temp-docs.log
	echo "" >> ${pwd}/temp-docs.log
	echo "--------" >> ${pwd}/temp-docs.log
	echo "" >> ${pwd}/temp-docs.log

	sed -i.bak "12r ${pwd}/temp-docs.log" ${mdPath}
fi
}

updateDocUrlItem $new_version

# 保留新版本号
echo "$new_version" > ${pwd}/docs/.vuepress/public/docs/versions.tag