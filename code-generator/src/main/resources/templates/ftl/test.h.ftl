class ${table.name} : C_DataObject
{
private:
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields! as field>
    ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

public:
${table.name}();
${table.name}(int id, int paper_id, int responder_user_id, QString selects);
~${table.name}();

bool Insert()
{
QString sql = "INSERT INTO ${table.name}(id,paper_id,responder_user_id,selects) VALUES(" + ToString() + "')";
return C_Database::GetInstance().__Exec_And_Return(sql);
}
};