export default {
  c: {
    machineName: '机器名称',
    nodeAddress: '节点地址',
    createTime: '创建时间',
    batchOperation: '批量操作',
    currentStatus: '当前状态：',
    statusMessage: '状态消息：',
    noStatusMessage: '还没有状态消息',
    edit: '编辑',
    assign: '分配',
    delete: '删除',
    nodeName: '节点名称：',
    templateNode: '模板节点',
    isTemplateNode: '是',
    isNotTemplateNode: '否',
    selectWorkspace: '请选择工作空间',
    configWithoutOverwrite: '不用挨个配置。配置后会覆盖之前的配置',
    useForConsistentEnv: '一般用于节点环境一致的情况',
    systemPrompt: '系统提示',
    confirm: '确认',
    cancel: '取消',
    noConfigTemplateNode: '还没有配置模板节点'
  },
  p: {
    noAssetMachine: '没有资产机器',
    pluginVersion: '插件版本',
    group: '分组',
    selectSortField: '请选择排序字段',
    networkDelay: '网络延迟',
    disk: '硬盘',
    memory: '内存',
    updateTime: '更新时间',
    search: '搜索',
    add: '新增',
    assignNode: '分配节点',
    syncAuth: '同步授权',
    syncSystemConfig: '同步系统配置',
    useTableViewForSync: '表格视图才能使用同步配置功能',
    nodeAccountInfo: '节点账号密码为插件端的账号密码,并非用户账号(管理员)密码',
    defaultAccountInfo:
      '节点账号密码默认由系统生成：可以通过插件端数据目录下 agent_authorize.json 文件查看（如果自定义配置了账号密码将没有此文件）',
    nodeAddressInfo: '节点地址为插件端的 IP:PORT 插件端端口默认为：2123',
    systemName: '系统名称:',
    systemVersion: '系统版本:',
    systemLoad: '系统负载:',
    pluginVersionField: '插件版本:',
    details: '详情',
    node: '节点',
    editMachine: '编辑机器',
    machineGroup: '机器分组',
    addGroup: '新增分组',
    selectGroupName: '选择分组名',
    nodeAddressInfo2: '节点地址为插件端的 IP:PORT 插件端端口默认为：212',
    useInternalAddress: '节点地址建议使用内网地址',
    connectionTroubleshoot: '如果插件端正常运行但是连接失败请检查端口是否开放,防火墙规则,云服务器的安全组入站规则',
    nodeAddressFormat: '节点地址格式为：IP:PORT (示例：192.168.1.100:2123)',
    nodeAddressExample: '节点地址 (192.168.1.100:2123)',
    protocolType: '协议类型',
    nodeAccount: '节点账号',
    nodeAccountInfo_1: '节点账号,请查看节点启动输出的信息',
    defaultAccount: '默认账号为：jpomAgent',
    nodePassword: '节点密码',
    accountPasswordInfo: '节点账号密码默认由系统生成：可以通过插件端数据目录下 agent',
    fileView: '文件查看（如果自定义配置了账号密码将没有此文件）',
    nodePasswordInfo: '节点密码,请查看节点启动输出的信息',
    otherConfig: '其他配置',
    useAsTemplate: '以此机器节点配置为模板',
    syncConfig: '用于快捷同步其他机器节点的配置',
    timeout: '超时时间(s)',
    timeoutUnit: '秒 (值太小可能会取不到节点状态)',
    proxy: '代理',
    proxyAddress: '代理地址 (127.0.0.1:8888)',
    proxyType: '选择代理类型',
    encoding: '编码方式',
    selectEncoding: '请选择编码方式',
    noEncoding: '不编码',
    assignWorkspace: '分配到其他工作空间',
    selectWorkspace: '选择工作空间',
    associatedNode: '关联节点',
    deleteNodeInfo: '已经分配到工作空间的机器无非直接删除，需要到分配到的各个工作空间逐一删除后才能删除资产机器',
    belongToWorkspace: '所属工作空间： ',
    syncAuth_1: '同步节点授权',
    syncAuthConfig: '一键分发同步多个节点的授权配置',
    selectTemplateNode: '请选择模板节点',
    projectPath: '项目路径',
    projectPathAuth: '请输入项目存放路径授权，回车支持输入多个路径，系统会自动过滤 ../ 路径、不允许输入根路径',
    fileExtension: '文件后缀',
    allowedFileExtension:
      '请输入允许编辑文件的后缀及文件编码，不设置编码则默认取系统编码，示例：设置编码：txt@utf-8， 不设置编码：txt',
    syncNodeConfig: '同步节点配置',
    save: '保存',
    saveAndRestart: '保存并重启',
    syncSystemConfig_1: '一键分发同步多个节点的系统配置',
    templateNode: '模版节点',
    selectTemplate: '请选择模版节点',
    machineName: '请输入机器的名称',
    name: '名称',
    systemName_1: '系统名',
    hostName: '主机名',
    groupName: '分组名',
    status: '状态',
    bootTime: '开机时间',
    usage: '占用',
    actualMemoryUsage: '实际内存占用',
    diskUsage: '硬盘占用',
    pluginVersion_1: '插件版本号',
    modifyTime: '修改时间',
    operation: '操作',
    confirmDeleteMachine: '真的要删除机器么？删除会检查数据关联性',
    syncAuthNode: '请选择要同步授权的机器节点',
    syncConfigNode: '请选择要同步系统配置的机器节点',
    confirmSaveConfig:
      '真的要保存当前配置吗？如果配置有误,可能无法启动服务需要手动还原奥！！！ 保存成功后请及时关注重启状态！！',
    confirmSaveConfigWarning: '真的要保存当前配置吗？如果配置有误,可能无法启动服务需要手动还原奥！！！'
  }
}