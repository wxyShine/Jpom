/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Code Technology Studio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.jpom.system;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.system.SystemUtil;
import io.jpom.JpomApplication;
import io.jpom.common.JpomManifest;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.File;
import java.nio.charset.Charset;
import java.util.function.Function;

/**
 * 外部资源配置
 *
 * @author jiangzeyin
 * @since 2019/4/16
 */
public class ExtConfigBean {

    public static final String FILE_NAME = "application.yml";

    /**
     *
     */
    private static Charset consoleLogCharset;

    public static void setConsoleLogCharset(Charset consoleLogCharset) {
        ExtConfigBean.consoleLogCharset = consoleLogCharset;
    }

    public static Charset getConsoleLogCharset() {
        return ObjectUtil.defaultIfNull(consoleLogCharset, CharsetUtil.systemCharset());
    }

    /**
     * 项目运行存储路径
     */
    private static String path;

    public static void setPath(String path) {
        ExtConfigBean.path = path;
    }

    /**
     * 动态获取外部配置文件的 resource
     *
     * @return File
     */
    public static Resource getResource() {
        String property = SpringUtil.getApplicationContext().getEnvironment().getProperty(ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY);
        Resource configResource = Opt.ofBlankAble(property)
            .map(FileSystemResource::new)
            .flatMap((Function<Resource, Opt<Resource>>) resource -> resource.exists() ? Opt.of(resource) : Opt.empty())
            .orElseGet(() -> {
                ClassPathResource classPathResource = new ClassPathResource(ExtConfigBean.FILE_NAME);
                return classPathResource.exists() ? classPathResource : new ClassPathResource("/config_default/" + FILE_NAME);
            });
        Assert.state(configResource.exists(), "均未找到配置文件");
        return configResource;
    }


    public static String getPath() {
        if (StrUtil.isEmpty(path)) {
            if (JpomManifest.getInstance().isDebug()) {
                // 调试模式 为根路径的 jpom文件
                String oldPath = ((SystemUtil.getOsInfo().isMac() ? "~" : "") + "/jpom/" + JpomApplication.getAppType().name() + StrUtil.SLASH).toLowerCase();
                File newFile = FileUtil.file(FileUtil.getUserHomeDir(), "jpom", JpomApplication.getAppType().name().toLowerCase());
                String absolutePath = FileUtil.getAbsolutePath(newFile);
                if (FileUtil.exist(oldPath) && !FileUtil.equals(newFile, FileUtil.file(oldPath))) {
                    FileUtil.move(FileUtil.file(oldPath), newFile, true);
                    Console.log("数据目录位置发生变化：{} => {}", oldPath, absolutePath);
                }
                //Console.log("本地运行存储的数据：{}", absolutePath);
                path = absolutePath;
            } else {
                // 获取当前项目运行路径的父级
                File file = JpomManifest.getRunPath();
                if (!file.exists() && !file.isFile()) {
                    throw new JpomRuntimeException("请配置运行路径属性【jpom.path】");
                }
                File parentFile = file.getParentFile().getParentFile();
                path = FileUtil.getAbsolutePath(parentFile);
            }
        }
        return path;
    }


}