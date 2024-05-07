#ifndef ${macroName}
#define ${macroName}

#include
<QObject>
    #include "public_head.h"
    #include "c_dataobject.h"
    #include "c_database.h"

    class ${table.name} : public C_DataObject
    {
    public:
    <#list table.fields! as field>
        ${field.propertyType} ${field.propertyName};
    </#list>
    ${table.name}()
    {
    <#list table.fields! as field>
        ${field.propertyName} = <#if field.propertyType == "int">0<#elseif field.propertyType == "double">0.0<#else>""</#if>;
    </#list>
    }

    bool Insert() override
    {
    QString sql =
    "INSERT INTO ${table.name}" +
    "(<#list table.fields! as field>${field.propertyName},</#list>) VALUES(" + QString::number(id) + "," +
    <#list table.fields! as field>
        <#if field.propertyName == "id"><#break></#if>
        <#if field.propertyType != "QString">
            QString::number(${field.propertyName}) + "," +
        <#else>
            "'${field.propertyName}'" + "," +
        </#if>
    </#list>

    LOG_INFO << sql;

    return C_Database::GetInstance().__Exec_And_Return(sql);
    }

    bool Delete() override
    {
    if (id == 0)
    {
    LOG_ERROR << "id is 0,can't delete";
    return false;
    }
    QString sql = "DELETE FROM ${table.name} WHERE id=" + QString::number(id);
    LOG_INFO << sql;
    return C_Database::GetInstance().__Exec_And_Return(sql);
    }

    bool Update() override
    {
    QString sql =
    "UPDATE ${table.name} SET " +
    <#--<#list table.fields! as field>-->
    <#list 0..(table.fields!?size-1) as i>
        <#if  table.fields[i].propertyType != "QString">
            <#if i != 0>"," + </#if>"${table.fields[i].propertyName}=" + QString::number(${table.fields[i].propertyName})+
        <#else>
            <#if i != 0>"," + </#if>"${table.fields[i].propertyName}=" + "'"+${table.fields[i].propertyName} + "'" +
        </#if>
    </#list>
    "' WHERE id=" +
    QString::number(id);
    return C_Database::GetInstance().__Exec_And_Return(sql);
    }

    bool Query(QVector
    <C_DataObject *> &result) override
        {
        // 拼接sql语句
        QString sql = "SELECT * FROM ${table.name}" + TableName;
        QStringList list;

        <#list table.fields! as field>
            <#if field.propertyType != "QString">
                if (${field.propertyName} != 0)
                list.append("${field.propertyName}=" + QString::number(${field.propertyName}));
            <#else>
                if (${field.propertyName} != '')
                list.append("${field.propertyName}='" + ${field.propertyName}+ "'");
            </#if>
        </#list>
        if (!list.empty())
        {
        sql += " WHERE ";
        sql += list.join(" AND ");
        }
        LOG_INFO << sql;

        // 执行sql语句
        if (!C_Database::GetInstance().__Exec_And_Return(sql))
        {
        return false;
        }

        // 获取数据
        result.clear();
        QSqlQuery &query = C_Database::GetInstance().GetSqlQuery();
        while (query.next())
        {
        ${table.name} *dataObject = new ${table.name}();
        <#list table.fields! as field>
            <#if field.propertyType == "int">
                dataObject->${field.propertyName} = query.value("${field.propertyName}").toInt();
            <#elseif field.propertyType="double" >
                dataObject->${field.propertyName} = query.value("${field.propertyName}").toDouble();
            <#else>
                dataObject->${field.propertyName} = query.value("${field.propertyName}").toString();
            </#if>
        </#list>
        result.push_back(dataObject);
        }
        query.finish();
        return true;
        }

        void Print() override
        {
        LOG_INFO
        <#list table.fields! as field>
        << "${field.propertyName}=" << ${field.propertyName}
        </#list>;
        }

        ~${table.name}() override{};
        };


        #endif // ${macroName}
