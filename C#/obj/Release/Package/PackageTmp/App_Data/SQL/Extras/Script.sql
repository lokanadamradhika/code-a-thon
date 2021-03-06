/****** Object:  Table [dbo].[Companies]    Script Date: 03/08/2011 00:06:47 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Companies]') AND type in (N'U'))
DROP TABLE [dbo].[Companies]
GO
/****** Object:  Table [dbo].[Companies]    Script Date: 03/08/2011 00:06:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Companies]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Companies](
	[CompanyId] [int] IDENTITY(1,1) NOT NULL,
	[CompanyName] [nvarchar](50) NULL,
	[ContactPhone] [nvarchar](50) NULL,
	[ContactEmail] [nvarchar](50) NULL,
 CONSTRAINT [PK_Companies] PRIMARY KEY CLUSTERED 
(
	[CompanyId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET IDENTITY_INSERT [dbo].[Companies] ON
INSERT [dbo].[Companies] ([CompanyId], [CompanyName], [ContactPhone], [ContactEmail]) VALUES (1, N'ABC Inc', N'111-222-3333', N'contact@abc.com')
INSERT [dbo].[Companies] ([CompanyId], [CompanyName], [ContactPhone], [ContactEmail]) VALUES (2, N'XYZ Inc', N'222-333-4444', NULL)
INSERT [dbo].[Companies] ([CompanyId], [CompanyName], [ContactPhone], [ContactEmail]) VALUES (3, N'Microsoft Crporation', N'123-456-7899', N'info@micrsoft.com')
SET IDENTITY_INSERT [dbo].[Companies] OFF
